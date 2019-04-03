/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.carrito;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandu
 */
public class Documento {

    private Document doc;
    private Font font;
    private Image image1;
    private Ticket ticket;

    public Documento() {
        this.font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        image1 = null;
        ticket = null;
        doc = new Document();
    }

    public Documento(Ticket ticket) {
        this.ticket = ticket;
        doc = new Document();
    }

    public void addTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void estructuraDoc(String path) throws DocumentException {
        //creacion de contenedor de imagen del logo
        try {
            String current = new java.io.File(".").getCanonicalPath();
            image1 = Image.getInstance(current + "/remote.png");
            image1.setAbsolutePosition(250f, 700f);
            image1.scaleAbsolute(100, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path+"ticket" + this.ticket.getId() + ".pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
        }
        doc.open();
        doc.add(image1);
        //nombre de la tienda
        Paragraph paragraph = new Paragraph("GAMING STORE");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph(" "));
        doc.add(paragraph);
        //se agrega contenido del ticket
        Paragraph contenidoTicket = new Paragraph(ticket.toString());
        contenidoTicket.setAlignment(Element.ALIGN_CENTER);
        doc.add(contenidoTicket);
        doc.close();

    }
    //
    //Image image1 = null;

}
