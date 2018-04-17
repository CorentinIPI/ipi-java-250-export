package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

    @Autowired
    private EntityManager em;

    public void insertTestData() {

        Client client  = new Client();
        client.setNom("PETRILLO");
        client.setPrenom("Alexandre");
        em.persist(client);

        Client client1  = new Client();
        client1.setNom("FOYARD");
        client1.setPrenom("Remi");
        em.persist(client1);
        
        Client client2  = new Client();
        client2.setNom("PINT;EAUX");
        client2.setPrenom("Julien");
        em.persist(client2);
        
        Article article = new Article();
        article.setLibelle("Carte mère ASROCK 2345");
        article.setPrix(79.90);
        em.persist(article);

        Article article1 = new Article();
        article1.setLibelle("Carte graphique GTX 960");
        article1.setPrix(259.90);
        em.persist(article1);
        
        Article article2 = new Article();
        article2.setLibelle("Iphone X");
        article2.setPrix(779.90);
        em.persist(article2);
        
        Facture facture = new Facture();
        facture.setClient(client);
        em.persist(facture);
        
        Facture facture1 = new Facture();
        facture1.setClient(client1);
        em.persist(facture1);
        
        Facture facture2 = new Facture();
        facture2.setClient(client2);
        em.persist(facture2);

        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(1);
        em.persist(ligneFacture1);
        
        LigneFacture ligneFacture2 = new LigneFacture();
        ligneFacture2.setFacture(facture);
        ligneFacture2.setArticle(article1);
        ligneFacture2.setQuantite(2);
        em.persist(ligneFacture2);
        
        LigneFacture ligneFacture3 = new LigneFacture();
        ligneFacture3.setFacture(facture1);
        ligneFacture3.setArticle(article2);
        ligneFacture3.setQuantite(6);
        em.persist(ligneFacture3);
        
        LigneFacture ligneFacture4 = new LigneFacture();
        ligneFacture4.setFacture(facture2);
        ligneFacture4.setArticle(article1);
        ligneFacture4.setQuantite(10);
        em.persist(ligneFacture4);

    }
}
