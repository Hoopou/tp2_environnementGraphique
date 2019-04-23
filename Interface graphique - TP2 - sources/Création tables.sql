
drop table if exists `tp2_graphique_bibliotheque_musique`.`albums`;
drop table if exists `tp2_graphique_bibliotheque_musique`.`artistes`;
drop table if exists `tp2_graphique_bibliotheque_musique`.`utilisateurs`;


CREATE TABLE IF NOT EXISTS `tp2_graphique_bibliotheque_musique`.`utilisateurs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `mdp` blob,
  `admin` boolean default 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  INSERT INTO `tp2_graphique_bibliotheque_musique`.`utilisateurs` (`nom`, `mdp`, `admin`) VALUES ('Admin', AES_ENCRYPT('password', 'salut'), '1');

CREATE TABLE IF NOT EXISTS `tp2_graphique_bibliotheque_musique`.`artistes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `membre` TINYINT NOT NULL,
  `photo` LONGBLOB NULL,
  `createur` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_utilisateur_artiste_idx` (`createur` ASC) VISIBLE,
  CONSTRAINT `fk_utilisateur_artiste`
    FOREIGN KEY (`createur`)
    REFERENCES `tp2_graphique_bibliotheque_musique`.`utilisateurs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
	INSERT INTO `tp2_graphique_bibliotheque_musique`.`artistes` (`nom`, `membre`, `createur`) VALUES ('Celine Dion', '1', '1');
    
CREATE TABLE IF NOT EXISTS `tp2_graphique_bibliotheque_musique`.`albums` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `genre` ENUM( 'Folk', 'Jazz', 'Classique', 'Hip-Hop', 'Pop', 'Rock','Alternative', 'Indie Rock', 'Franco'),  ##devrait etre une bd
  `annee_sortie` DATE NOT NULL,
  `couverture` LONGBLOB NULL,
  `idArtiste` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_artistes_albums_idx` (`idArtiste` ASC) VISIBLE,
  CONSTRAINT `fk_artistes_albums`
    FOREIGN KEY (`idArtiste`)
    REFERENCES `tp2_graphique_bibliotheque_musique`.`artistes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
INSERT INTO `tp2_graphique_bibliotheque_musique`.`albums` (`titre`, `genre`, `annee_sortie`, `idArtiste`) VALUES ('S\'il suffisait d\'aimer', 'pop', '1998-11-7', '1');

##Exemple de requete pour afficher toutes les albums et informations de l'artiste ayant l'id 1
SELECT * FROM tp2_graphique_bibliotheque_musique.artistes inner join tp2_graphique_bibliotheque_musique.albums on idArtiste=artistes.id where idartiste=1;

##exemple de requete pour le mot de passe
Select aes_decrypt(mdp, 'salut') from utilisateurs;


    
    
    
    
