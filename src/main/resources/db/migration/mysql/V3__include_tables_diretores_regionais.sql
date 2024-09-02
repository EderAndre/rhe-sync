CREATE TABLE rhe_dados_dir_reg(
    ROWID int(11) AUTO_INCREMENT NOT NULL,
    NUMFUNCTIT varchar(50) NULL,
    NUMVINCTIT varchar(50) NULL,
    DTINITIT date NULL,
    DTFIMTIT date NULL,
    MUNICIPIO varchar(255) NULL,
    NUMFUNCSUBST varchar(50) NULL,
    NUMVINCSUBST varchar(50) NULL,
    DTINISUBST date NULL,
    DTFIMSUBST date NULL,
    IDENTIFICADOR varchar(255)NULL,
    CREATED datetime NULL, 
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);
