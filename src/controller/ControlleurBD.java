package controller;
import Main.Constantes;
import Objects.Albums;
import Objects.Artistes;
import Objects.Utilisateurs;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Icon;

import com.mysql.jdbc.Blob;

public class ControlleurBD {
	public static int OK = 0;
	public static int UTILISATEUR_EXISTANT = 1062;
	public static int VALEUR_INVALIDE = 2;
	public static int ERREUR = 100;
	
	public static int AjouterUtilisateur(String utilisateur , String mdp , int admin) {
		Statement stmt = null;
        ResultSet rs = null;
        int response = 0;
        try {
            stmt = getConnection().createStatement();
            
            //System.out.println("INSERT INTO utilisateurs (`nom`, `mdp`, `admin`) VALUES('" +utilisateur+"',  AES_ENCRYPT('" + mdp + "','"+Constantes.BD_ENCRYPTION_STRING+"'), '" + admin + "');");
            response = stmt.executeUpdate("INSERT INTO utilisateurs (`nom`, `mdp`, `admin`) VALUES('" +utilisateur+"',  AES_ENCRYPT('" + mdp + "','"+Constantes.BD_ENCRYPTION_STRING+"'), '" + admin + "');");
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        	return UTILISATEUR_EXISTANT;
        }
        
        if(response == Statement.EXECUTE_FAILED) {
        	return VALEUR_INVALIDE;
        }else if(response == Statement.SUCCESS_NO_INFO || response == 1) {
        	return OK;
        }else {
        	return ERREUR;
        }
	}
	
	public static Utilisateurs GetUtilisateur(String utilisateur , String mdp) {
		Statement stmt = null;
        ResultSet rs = null;
        Utilisateurs user = new Utilisateurs();
        
        try {
            stmt = getConnection().createStatement();
            
            System.out.println("SELECT id,nom,aes_decrypt(mdp, '" + Constantes.BD_ENCRYPTION_STRING+ "'),admin FROM utilisateurs WHERE nom = '" + utilisateur + "' && mdp = AES_ENCRYPT('" + mdp + "','"+Constantes.BD_ENCRYPTION_STRING+"')");
            rs = stmt.executeQuery("SELECT id,nom,aes_decrypt(mdp, '" + Constantes.BD_ENCRYPTION_STRING+ "'),admin FROM utilisateurs WHERE nom = '" + utilisateur + "' && mdp = AES_ENCRYPT('" + mdp + "','"+Constantes.BD_ENCRYPTION_STRING+"')");
            
            while(rs.next()) {
            	user.setId(rs.getInt(1));
            	user.setNom(rs.getString(2));
            	user.setMdp(rs.getString(3)); 
            	user.setAdmin(rs.getInt(4) == 0 ? false : true);
            	break;
            }
            
            if(user.getId() == null && user.getNom() == null) {
            	return null;
            }else {
            	return user;            	
            }
            
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        	return null;
        }
	}
	
	
	public static ArrayList<Artistes> GetArtistes() {
		Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Artistes> arrayArtistes = new ArrayList<Artistes>();
        Artistes artiste;
        
        try {
            stmt = getConnection().createStatement();
            
            System.out.println("SELECT * FROM artistes;");
            rs = stmt.executeQuery("SELECT * FROM artistes;");
            
            while(rs.next()) {
            	artiste = new Artistes();
            	artiste.setId(rs.getInt(1));
            	artiste.setNom(rs.getString(2));
            	artiste.setMembre(rs.getInt(3)==0?false:true); 
            	try {
//            		java.sql.Blob blob = ((java.sql.Blob)rs.getBinaryStream(4));
//            		byte[] imageInByte = blob.getBytes(1, (int)blob.length());
            		byte[] imageInByte = (rs.getBytes(4));
                	BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageInByte));
    				if(image == null) {
    					artiste.setPhoto(null);
    				}else {
    					artiste.setPhoto(image);            			
    				}
            	}catch(Exception e) {
            		
            	}
            	artiste.setCreateur(rs.getInt(5));
            	arrayArtistes.add(artiste);
            }
            
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        }
        
        return arrayArtistes;
	}
	
	public static ArrayList<Albums> GetAlbums() {
		Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Albums> arrayAlbums = new ArrayList<Albums>();
        Albums album;
        
        try {
            stmt = getConnection().createStatement();
            
            System.out.println("SELECT * FROM albums;");
            rs = stmt.executeQuery("SELECT * FROM albums;");
            
            while(rs.next()) {
            	album = new Albums();
            	album.setId(rs.getInt(1));
            	album.setTitre(rs.getString(2));
            	album.setGenre(rs.getString(3)); 
            	album.setAnneeSortie(rs.getString(4));
            	try {
            		java.sql.Blob blob = ((java.sql.Blob)rs.getBinaryStream(5));
            		byte[] imageInByte = blob.getBytes(1, (int)blob.length());
//            		byte[] imageInByte = rs.getBytes(5);
                	BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageInByte));
    				if(image == null) {
    					album.setCouverture(null);
    				}else {
    					album.setCouverture(image);            			
    				}
            	}catch(Exception e) {
            		
            	}
            	album.setIdArtiste(rs.getInt(6));
            	arrayAlbums.add(album);
            }
            
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        }
        
        return arrayAlbums;
	}
	
	public static int AjouterArtiste(Artistes artiste) {
		Statement stmt = null;
        ResultSet rs = null;
        int response = 0;
        try {
            stmt = getConnection().createStatement();
            
            System.out.println("INSERT INTO artistes (`id`, `nom`, `membre`, `photo` , `createur`) VALUES('" +artiste.getId()+"', '"+artiste.getNom()+"', '" + (artiste.getMembre()==true?1:0) + "','" + artiste.getPhoto() + "' , '" + artiste.getCreateur()+"');");
            response = stmt.executeUpdate("INSERT INTO artistes (`id`, `nom`, `membre`, `photo` , `createur`) VALUES('" +artiste.getId()+"', '"+artiste.getNom()+"', '" + (artiste.getMembre()==true?1:0) + "','" + artiste.getPhoto() + "' , '" + artiste.getCreateur() +"');");
            
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        	return UTILISATEUR_EXISTANT;
        }
        
        if(response == Statement.EXECUTE_FAILED) {
        	return VALEUR_INVALIDE;
        }else if(response == Statement.SUCCESS_NO_INFO || response == 1) {
        	return OK;
        }else {
        	return ERREUR;
        }
	}
	
	public static int DeleteArtiste(int artisteid) {
		Statement stmt = null;
        ResultSet rs = null;
        int response = 0;
        try {
            stmt = getConnection().createStatement();
            
            System.out.println("DELETE FROM artistes WHERE id = " + artisteid + ";");
            response = stmt.executeUpdate("DELETE FROM artistes WHERE id = " + artisteid + ";");
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        	return UTILISATEUR_EXISTANT;
        }
        
        if(response == Statement.EXECUTE_FAILED) {
        	return VALEUR_INVALIDE;
        }else if(response == Statement.SUCCESS_NO_INFO || response == 1) {
        	return OK;
        }else {
        	return ERREUR;
        }
	}
	
	public static int DeleteAlbum(int albumId) {
		Statement stmt = null;
        ResultSet rs = null;
        int response = 0;
        try {
            stmt = getConnection().createStatement();
            
            System.out.println("DELETE FROM albums WHERE id = " + albumId + ";");
            response = stmt.executeUpdate("DELETE FROM albums WHERE id = " + albumId + ";");
        }catch(SQLException e) {
        	System.err.println(e.getErrorCode());
        	return UTILISATEUR_EXISTANT;
        }
        
        if(response == Statement.EXECUTE_FAILED) {
        	return VALEUR_INVALIDE;
        }else if(response == Statement.SUCCESS_NO_INFO || response == 1) {
        	return OK;
        }else {
        	return ERREUR;
        }
	}
	
	public static int UpdateImageArtiste(Artistes artiste) {
		PreparedStatement stmt = null;
        ResultSet rs = null;
        int response = 0;
        try {
        	Connection conn = getConnection();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(artiste.getPhoto(), "jpg", baos );
            byte[] imageInByte = baos.toByteArray();
            stmt = conn.prepareStatement("UPDATE artistes set photo = ? where id= "+artiste.getId()+";");
//            java.sql.Blob b = conn.createBlob();
//            b.setBytes(, imageInByte);
            stmt.setBytes(1, imageInByte);
            System.out.println("UPDATE artistes set photo = '"+imageInByte+"' where id="+artiste.getId()+";");
            stmt.executeUpdate();
            
        }catch(Exception e) {
        	System.err.println(e.getMessage());
        	return UTILISATEUR_EXISTANT;
        }
        
        if(response == Statement.EXECUTE_FAILED) {
        	return VALEUR_INVALIDE;
        }else if(response == Statement.SUCCESS_NO_INFO || response == 1) {
        	return OK;
        }else {
        	return ERREUR;
        }
	}
	
	
	private static Connection getConnection() throws SQLException {
		String url1 = Constantes.BD_BASE_CONNECTION_STRING + Constantes.BD_ADRESSE + ":" + Constantes.BD_PORT + "/"+
		 		Constantes.BD_NAME;
		System.out.println(url1);
		return DriverManager.getConnection(url1, Constantes.BD_UTILISATEUR, Constantes.BD_MDP);
	}
}
