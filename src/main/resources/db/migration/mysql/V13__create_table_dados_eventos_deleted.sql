CREATE TABLE rhe_dados_evento_deleted(
    ROWID int(11) NOT NULL AUTO_INCREMENT,
    NUMEV varchar(50),
    OPERACAO varchar(1),
    TIPO varchar(1),
    NUMFUNC varchar(50) NULL,
    NUMVINC varchar(50) NULL,
    CARGO varchar(50) NULL,
    DTINI date NULL,
    DTFIM date NULL,
    MOTIVO varchar(50) NULL,
    REFERENCIA varchar(50) NULL,
    STATUS_OLD varchar(50) NULL,
    CREATED datetime DEFAULT NULL,
    LASTUPDATED datetime DEFAULT NULL,
    primary key(ROWID)
);