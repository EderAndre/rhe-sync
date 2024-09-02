CREATE TABLE rhe_dados_ferias_full(
    ROWID bigint(20) AUTO_INCREMENT NOT NULL,
    NUMFUNC int(11) NULL,
    NUMVINC int(2) NULL,
    DTINI date NULL,
    DTFIM date NULL,
    MESANOPAGTO varchar(50) NULL,
    NRODIASPAGTO varchar(50) NULL,
    DTINIPA date NULL,
    DTFIMPA date NULL,
    TOTALDIAS int(4) NULL,
    DIASVENDIDOS int(4) NULL,
    DIASGOZADOS int(4) NULL,
    SALDO int(4) NULL,
    OBS varchar(1020) NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);