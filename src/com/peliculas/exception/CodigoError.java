package com.peliculas.exception;

/**
 *
 * @author mreyesi
 */
public enum CodigoError {

	  OK(0, "OK"),
          ERROR_GENERICO(1, "Error Generico"),
	  ERROR_XML(100, "Error de Carga de Ficheros"),
          ERROR_CONEXION(101, "Error de conexion de MySQL"),
          ERROR_CONSULTAS(102, "Error de consultas"),
          ERROR_ARCHIVOS(103, "Error al listar archivos"),
          ERROR_SCRAPING(103, "Error buscar peli en la WEB"),
          ERROR_BUSCAR_PELICULAS(104, "Error al buscar peli en la WEB, no se encuentran resultados");

	  private final int codigo;
	  private final String descripcion;

	  private CodigoError(final int codigo, final String descripcion) {
	    this.codigo = codigo;
	    this.descripcion = descripcion;
	  }

	  public String getDescripcion() {
	     return descripcion;
	  }

	  public int getCodigo() {
	     return codigo;
	  }

	  @Override
	  public String toString() {
	    return codigo + ": " + descripcion;
	  }

}
