package br.rs.gov.defensoria.rhe.service

import br.rs.gov.defensoria.rhe.utils.Type
import br.rs.gov.defensoria.rhe.utils.TypesDefinition
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class MatchImporter {

    @Inject
    lateinit var funcional: ImportFuncional

    @Inject
    lateinit var afastamento: ImportAfastamento

    @Inject
    lateinit var capacitacao: ImportCapacitacao

    @Inject
    lateinit var publicacao: ImportPublicacao

    @Inject
    lateinit var dependencia: ImportDependencia

    @Inject
    lateinit var dependente: ImportDependente

    @Inject
    lateinit var diretorRegional: ImportDiretorRegional

    @Inject
    lateinit var eventoDeleted: ImportEventoDeleted

    @Inject
    lateinit var evento: ImportEvento

    @Inject
    lateinit var ferias: ImportFerias

    @Inject
    lateinit var feriasFull: ImportFeriasFull

    @Inject
    lateinit var feriasPend: ImportFeriasPend

    @Inject
    lateinit var licencaPremioPend: ImportLicencaPremioPend

    @Inject
    lateinit var licencaPremio: ImportLicencaPremio

    @Inject
    lateinit var formacao: ImportFormacao

    @Inject
    lateinit var vinculo: ImportVinculo

    @Inject
    lateinit var setorHistorico: ImportSetorHistorico

    @Inject
    lateinit var cargo: ImportCargo

    @Inject
    lateinit var cidade: ImportCidade

    @Inject
    lateinit var pais: ImportPais

    @Inject
    lateinit var subcategoria: ImportSubcategoria

    @Inject
    lateinit var tipoEvento: ImportTipoEvento

    @Inject
    lateinit var trinomio: ImportTrinomio

    @Inject
    lateinit var files: FilesService

    var type: TypesDefinition = TypesDefinition()

    private fun matchFile(fileName: String): Type? {
        val r = files.detectType(fileName)
        return r
    }

    fun dispatchFile(fileName: String): Any? {
        val match = matchFile(fileName)
        val importer = when (match?.name) {
            type.FUNCIONAL.name -> funcional.importData(fileName)
            type.AFASTAMENTO.name -> afastamento.importData(fileName)
            type.CAPACITACAO.name -> capacitacao.importData(fileName)
            type.DADOS_PUBLICACAO.name -> publicacao.importData(fileName)
            type.DEPENDENCIAS.name -> dependencia.importData(fileName)
            type.DEPENDENTES.name -> dependente.importData(fileName)
            type.DIRETORES_REGIONAIS.name -> diretorRegional.importData(fileName)
            type.EVENT_DELETED.name -> eventoDeleted.importData(fileName)
            type.EVENTO.name -> evento.importData(fileName)
            type.FERIAS.name -> ferias.importData(fileName)
            type.FERIAS_FULL.name -> feriasFull.importData(fileName)
            type.FERIAS_PENDENTES.name -> feriasPend.importData(fileName)
            type.FORMACOES.name -> formacao.importData(fileName)
            type.LICENCA_PREMIO.name -> licencaPremio.importData(fileName)
            type.LICENCA_PREMIO_PENDENTE.name -> licencaPremioPend.importData(fileName)
            type.VINCULO.name -> vinculo.importData(fileName)
            type.SETOR_HISTORICO.name -> setorHistorico.importData(fileName)
            type.CARGOS.name -> cargo.importData(fileName)
            type.MUNICIPIOS.name -> cidade.importData(fileName)
            type.PAIS.name -> pais.importData(fileName)
            type.SUBCATEGORIA.name -> subcategoria.importData(fileName)
            type.TIPOEVENTOS.name -> tipoEvento.importData(fileName)
            type.TRINOMIOS.name -> trinomio.importData(fileName)
            else -> null
        }
        return importer
    }
}