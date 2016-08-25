drop table ARESTA;
drop table VERTICE;

-- -----------------------------------------------------
-- Table `mydb`.`VERTICE`
-- -----------------------------------------------------
CREATE TABLE VERTICE (
  ID NUMBER(20) NOT NULL,
  DESCRICAO VARCHAR2(45) NULL,
  PRIMARY KEY (ID));

-- -----------------------------------------------------
-- Table `mydb`.`ARESTA`
-- -----------------------------------------------------
CREATE TABLE ARESTA (
  ID NUMBER(20) NOT NULL,
  DISTANCIA NUMBER(10) NOT NULL, 
  FK_VERTICE_ORIGEM NUMBER(20) NOT NULL,
  FK_VERTICE_DESTINO NUMBER(20) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT FK_ARESTA_VERTICE FOREIGN KEY (FK_VERTICE_ORIGEM) REFERENCES VERTICE (ID),
  CONSTRAINT FK_ARESTA_VERTICE1 FOREIGN KEY (FK_VERTICE_DESTINO) REFERENCES VERTICE (ID));
