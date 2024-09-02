CREATE TABLE rhe_dados_dependencias(
    id_funcional varchar(50) NOT NULL,
    nr_dependente varchar(50) NOT NULL,
    dt_inicio date NOT NULL,
    dt_termino date NULL,
    no_tipo_dependencia varchar(255) NOT NULL,
    CREATED datetime DEFAULT NULL,
    LASTUPDATED datetime DEFAULT NULL,
    PRIMARY KEY  (id_funcional,nr_dependente,no_tipo_dependencia,dt_inicio)
);