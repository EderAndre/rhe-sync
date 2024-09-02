CREATE TABLE rhe_dados_formacoes(
    ROWID int IDENTITY,
    NUMFUNC int NOT NULL,
    CURSOFORM varchar(255) NULL,
    HABILITACAO varchar(255) NULL,
    ESCOLA varchar(255) NULL,
    DTINI date NULL,
    DTFIM date NULL,
    CARGAHOR varchar(255) NULL,
    PTSCONTA varchar(255) NULL,
    VALIDPROMO varchar(255) NULL,
    PAGO varchar(255) NULL,
    PONTOLIB varchar(255) NULL,
    BOLSA varchar(255) NULL,
    DESCRICAO varchar(255) NULL,
    DATA date NULL,
    OBS varchar(255) NULL,
    CREATED datetime NULL,
    LASTUPDATED datetime NULL,
    primary key (ROWID)
);

