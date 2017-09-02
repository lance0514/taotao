//package com.solr;
//
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
//import org.apache.solr.common.SolrInputDocument;
//import org.junit.Test;
//
//import java.io.IOException;
//
//
//public class SolrTest {
//
//@Test
//public void addDocument() throws IOException, SolrServerException {
//
//    SolrServer solrServer = new HttpSolrServer("http://localhost:8983/solr/");
//
//
//    SolrInputDocument solrInputDocument = new SolrInputDocument();
//
//    solrInputDocument.addField("id","test001");
//    solrInputDocument.addField("item_title","测试商品");
//    solrInputDocument.addField("item_title","测试商品");
//    solrInputDocument.addField("item_price","199");
//
//    solrServer.add(solrInputDocument);
//
//
//    solrServer.commit();
//
//}
//
//
//
//
//
//}
