package com.myblog.lucence;

import com.myblog.entity.Blog;
import com.myblog.util.DateUtil;
import com.myblog.util.StringUtil;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Component
public class BlogIndex {

    //创建索引
    public Directory directory;
    //索引存储目录
    public String indexDir = "J:/resourcecode/myBlog/src/main/webapp/static/luceneIndex/";

    public IndexWriter getIndexWriter() throws IOException {
        //实例化索引目录
        directory = FSDirectory.open(Paths.get(indexDir));
        //建立解析器
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
        //设置初始配置
        IndexWriterConfig config = new IndexWriterConfig(smartChineseAnalyzer);
        //初始化IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory,config);
        //拿到句柄
        return indexWriter;
    }

    public void addIndex(Blog blog) throws IOException {
        //拿到IndexWriter
        IndexWriter indexWriter = getIndexWriter();
        //实例化索引文档
        Document document = new Document();
        //设置字段
        document.add(new TextField("title",blog.getTitle(), Field.Store.YES));
        document.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
        document.add(new TextField("content",blog.getContent(),Field.Store.YES));
        document.add(new StringField("releaseDate", DateUtil.dateFormat(blog.getReleaseDate(),"yyyy-mm-dd"),Field.Store.YES));
        //添加文档
        indexWriter.addDocument(document);
        //关闭流
        indexWriter.close();
    }

    public void deleteIndex(String blogId) throws IOException {
        IndexWriter indexWriter = getIndexWriter();

        indexWriter.deleteDocuments(new Term("id",blogId));
        //直接强制删除，不保存回收站
        indexWriter.forceMergeDeletes();
        //保证实时性
        indexWriter.commit();

        indexWriter.close();
    }

    public void updateIndex(Blog blog) throws Exception {
        IndexWriter indexWriter = getIndexWriter();

        //新建文档
        Document document = new Document();
        document.add(new TextField("title",blog.getTitle(), Field.Store.YES));
        document.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
        document.add(new TextField("content",blog.getContent(),Field.Store.YES));
        document.add(new StringField("releaseDate", DateUtil.dateFormat(blog.getReleaseDate(),"yyyy-mm-dd"),Field.Store.YES));
        //更新
        indexWriter.updateDocument(new Term("id",String.valueOf(blog.getId())),document);

        indexWriter.close();
    }

    public List<Blog> searchBlog(String str) throws Exception{
        //去除前后空格后判断是否为空
        str=str.trim();
        if(str==null||str.length()==0)return null;
        //实例化索引目录
        directory = FSDirectory.open(Paths.get(indexDir));

        //创建IndexSearcher对象,用于查询索引库
        IndexReader indexReader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //创建查询语句，用于将多个条件连接在一起进行查询
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

        //中文分析器
        SmartChineseAnalyzer chineseAnalyzer = new SmartChineseAnalyzer();

        //标题查询分析器
        QueryParser titleParser = new QueryParser("title",chineseAnalyzer);
        Query titleQuery = titleParser.parse(str);
        //内容查询分析器
        QueryParser contentParser = new QueryParser("content",chineseAnalyzer);
        Query contentQuery = contentParser.parse(str);

        //将标题和内容联结查询
        //将两个查询器添加到Query，逻辑关系为或者
        booleanQuery.add(titleQuery, BooleanClause.Occur.SHOULD);
        booleanQuery.add(contentQuery, BooleanClause.Occur.SHOULD);

        //记录100条查询数据
        TopDocs hits = indexSearcher.search(booleanQuery.build(),100);

        //计算得分，主要看重对title的匹配
        QueryScorer queryScorer = new QueryScorer(titleQuery);

        //创建并格式化得分高的片段
        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");

        //高亮显示
        Highlighter highlighter = new Highlighter(formatter,queryScorer);
        highlighter.setTextFragmenter(fragmenter);

        List<Blog> list = new LinkedList<>();
        for(ScoreDoc scoreDoc : hits.scoreDocs){
            Document doc = indexSearcher.doc(scoreDoc.doc);
            Blog blog = new Blog();
            blog.setId(Integer.parseInt(doc.get("id")));
            blog.setReleaseDateStr(doc.get("releaseDate"));
            String title = doc.get("title");
            String content = doc.get("content");
            if(title != null) {
                TokenStream tokenStream = chineseAnalyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if(StringUtil.isEmpty(hTitle)) {
                    blog.setTitle(title);
                } else {
                    blog.setTitle(hTitle);
                }
            }
            if(content != null) {
                TokenStream tokenStream = chineseAnalyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if(StringUtil.isEmpty(hContent)) {
                    if(content.length() > 100) { //如果没查到且content内容又大于100的话
                        blog.setContent(content.substring(0, 100)); //截取100个字符
                    } else {
                        blog.setContent(content);
                    }
                } else {
                    blog.setContent(hContent);
                }
            }
            list.add(blog);
        }

        return list;
    }

}














