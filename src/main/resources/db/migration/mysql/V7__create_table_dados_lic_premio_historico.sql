CREATE TABLE rhe_dados_lic_premio_historico(
    ROWID bigint(20) AUTO_INCREMENT NOT NULL,
    NUMFUNC int(11) NULL,
    NUMVINC int(2) NULL,
    DTINI date NULL,
    DTPREVFIM date NULL,
    DTFIM date NULL,
    DTINIPA date NULL,
    DTFIMPA date NULL,
    OBS varchar(510) NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);
CREATE TABLE rhe_dados_lic_premio_pend_historico(
    ROWID bigint(20) AUTO_INCREMENT NOT NULL,
    NUMFUNC int(11) NULL,
    NUMVINC int(2) NULL,
    DTINI date NULL,
    DTFIM date NULL,
    TOTALDIAS int(4) NULL,
    DIAS_TIRADOS int(4) NULL,
    DIAS_VENDIDOS int(4) NULL,
    CONT_DOBRO int(4) NULL,
    SALDO int(4) NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);
