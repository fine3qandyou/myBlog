package com.myblog.lucence;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class BlogIndex {

    //创建索引
    public Directory directory;
    //索引存储目录
    public String indexDir = "J:/resourcecode/myBlog/src/main/webapp/static/lucenceIndex/";

    public IndexWriter getIndexWriter() throws IOException {
        directory = FSDirectory.open(Paths.get(indexDir));
    }

}

