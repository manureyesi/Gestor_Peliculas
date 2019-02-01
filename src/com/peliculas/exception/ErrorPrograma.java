package com.peliculas.exception;

/**
 *
 * @author mreyesi
 */
public class ErrorPrograma extends Exception {
    
    private static final long serialVersionUID = -2027206562681810733L;
    private final CodigoError codError;
    
    public ErrorPrograma(CodigoError codErr){
        this.codError = codErr;
    }
    
    public int getCodigoError() {
	return this.codError.getCodigo();
    }

    public String getDescripcionError() {
        return this.codError.getDescripcion();
    }
    
    @Override
    public String toString(){
        return this.codError.toString();
    }
}
