package br.rs.gov.defensoria.rhe.utils


import jakarta.inject.Singleton

@Singleton
class TypesDefinition {
        val AFASTAMENTO = Type(
            name = "Afastamentos",
            expressionFromDetection = "_AFAST-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(1, 16, 32, 33, 43, 53, 113, 123),
            columnLabels = listOf(
                "OPERACAO",
                "CHAVE",
                "NUMFUNC",
                "NUMVINC",
                "DTINI",
                "DTFIM",
                "MOTIVO",
                "DTPREVFIM"
            )
        )

        val EVENT_DELETED = Type(
            name = "Eventos Exluídos",
            expressionFromDetection = "EXPDADOS_EVENTOS-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(1, 16, 17, 26, 34, 40, 50, 60, 80, 90),
            columnLabels = listOf(
                "OPERACAO",
                "NUMEV",
                "TIPO",
                "NUMFUNC",
                "NUMVINC",
                "CARGO",
                "DTINI",
                "DTFIM",
                "MOTIVO",
                "REFERENCIA"
            )
        )

        val DIRETORES_REGIONAIS = Type(
            name = "DiretoresRegionais",
            expressionFromDetection = "_COMPL_DIRETORES_REGIONAIS_E_SUBSTITUTOS-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val CARGOS = Type(
            name = "Cargos",
            expressionFromDetection = "_MESTRES_CARGOS-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(6, 76, 116, 117, 137, 157, 167),
            columnLabels = listOf(
                "COD_CARGO_FUNCAO",
                "NOME_CARGO_FUNCAO",
                "TIPO_CARGO",
                "CARGO_FUNCAO",
                "CATEGORIA",
                "SUBCATEGORIA",
                "DT_EXTINCAO"
            )
        )

        val DADOS_PUBLICACAO = Type(
            name = "DadosPublicacao",
            expressionFromDetection = "_COMPL_DADOS_PUBLICACAO-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val EVENTO = Type(
            name = "DadosEventos",
            expressionFromDetection = "_COMPL_EVENTO-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val FUNCIONAL = Type(
            name = "DadosFuncionais",
            expressionFromDetection = "_COMPL_FUNCIONAL-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val MUNICIPIOS = Type(
            name = "MestreCidades",
            expressionFromDetection = "_MESTRES_MUNICIPIOS-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(5, 35),
            columnLabels = listOf(
                "ID_CIDADE",
                "NOME_CIDADE"
            )
        )

        val PAIS = Type(
            name = "MestrePais",
            expressionFromDetection = "_MESTRES_PAIS-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(5, 35),
            columnLabels = listOf(
                "ID_PAIS",
                "NACIONALIDADE"
            )
        )

        val SETOR = Type(
            name = "MestreSetor",
            expressionFromDetection = "COMPL_SETOR_OLD-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val SETOR_HISTORICO = Type(
            name = "MestreSetorHistorico",
            expressionFromDetection = "COMPL_SETOR-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val SUBCATEGORIA = Type(
            name = "MestreSubcateg",
            expressionFromDetection = "_MESTRES_SUBCATEG-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(20, 40),
            columnLabels = listOf("CATEGORIA", "SIGLA")
        )

        val TIPOEVENTOS = Type(
            name = "MestreTipoEventos",
            expressionFromDetection = "_MESTRES_TIPOEVENTOS-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(20, 80, 120, 140, 180),
            columnLabels = listOf(
                "TIPOEVENTO",
                "NOME",
                "NATUREZA",
                "NATUREZA_PRINCIPAL",
                "TIPO_CARGO"
            )
        )

        val TRINOMIOS = Type(
            name = "MestreTrinomios",
            expressionFromDetection = "_MESTRES_TRINOMIO-.\\d{6}.[_|.]",
            extension = "TXT",
            columnLimits = listOf(20, 40, 60),
            columnLabels = listOf(
                "TIPOVINC",
                "CATEGORIA",
                "REGIMEJUR"
            )
        )

        val VINCULO = Type(
            name = "DadosVinculo",
            expressionFromDetection = "_COMPL_VINCULO-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val FERIAS = Type(
            name = "Ferias",
            expressionFromDetection = "_COMPL_FERIAS-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val FERIAS_PENDENTES = Type(
            name = "FeriasPendentes",
            expressionFromDetection = "_COMPL_FERIAS_PEND-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val LICENCA_PREMIO = Type(
            name = "LicencaPremio",
            expressionFromDetection = "_COMPL_LIC_PREMIO-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val LICENCA_PREMIO_PENDENTE = Type(
            name = "LicencaPremioPendente",
            expressionFromDetection = "_COMPL_LIC_PREMIO_PEND-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val FERIAS_FULL = Type(
            name = "FeriasFull",
            expressionFromDetection = "__FERIAS_FULL-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val CAPACITACAO = Type(
            name = "Capacitacao",
            expressionFromDetection = "_COMPL_CAPACITACOES-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val FORMACOES = Type(
            name = "Formacoes",
            expressionFromDetection = "_COMPL_FORMACOES-.\\d{6}.[_|.]",
            extension = "CSV"
        )

        val DEPENDENTES = Type(
            name = "Dependentes",
            expressionFromDetection = "_COMPL_DEPENDENTES-.\\d{6}.[_|.]",
            extension = "CSV"
        )
        val DEPENDENCIAS = Type(
            name = "Dependencias",
            expressionFromDetection = "_COMPL_DEPENDENCIAS-.\\d{6}.[_|.]",
            extension = "CSV"
        )
}