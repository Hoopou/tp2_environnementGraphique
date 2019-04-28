package controller;
import Main.Constantes;
import Objects.Utilisateurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

public class ControlleurBD {
	public static int OK = 0;
	public static int UTILISATEUR_EXISTANT = 1062;
	public static int VALEUR_INVALIDE = 2;
	public static int ERREUR = 100;
	
	public static int AjoutUtilisateur(String utilisateur , String mdp , int admin) {
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
            	System.out.println();
            	user.setId(rs.getInt(1));
            	user.setNom(rs.getString(2));
            	user.setMdp(rs.getString(3)); //ne fonctionnera pas a cause de l'encription du mot de passe
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
	
	
	
	
	
	private static Connection getConnection() throws SQLException {
		String url1 = Constantes.BD_BASE_CONNECTION_STRING + Constantes.BD_ADRESSE + ":" + Constantes.BD_PORT + "/"+
		 		Constantes.BD_NAME;
		System.out.println(url1);
		return DriverManager.getConnection(url1, Constantes.BD_UTILISATEUR, Constantes.BD_MDP);
	}
}
