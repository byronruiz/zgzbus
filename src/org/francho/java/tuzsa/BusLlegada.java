/**
 *  ZgzBus - Consulta cuando llega el autobus urbano en Zaragoza
 *  Copyright (C) 2010 Francho Joven
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.francho.java.tuzsa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase contenedora de la estructura de datos para cada una de las llegadas de un poste
 * 
 * @author francho - http://francho.org/lab/
 * 
 */
public class BusLlegada implements Comparable<BusLlegada> {
	/**
	 * Número de línea
	 */
    private String linea;
    /**
     * Dirección
     */
    private String destino;
    /**
     * Próximo bus en...
     */
    private String proximo;

    /**
     * Constructor 
     * @param linea bus
     * @param destino dirección de de destino
     * @param proximo próximo bus en
     */
    public BusLlegada(String linea, String destino, String proximo) {
        this.linea = linea;
        this.destino = destino;
        this.proximo = proximo;
    }

	@Override
	public int compareTo(BusLlegada bus2) {
		Integer min1 = getProximoMinutos();
		Integer min2 = bus2.getProximoMinutos();
        return min1.compareTo(min2);
    }

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
		result = prime * result + ((this.getProximoMinutos() == null) ? 0 : getProximoMinutos().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusLlegada other = (BusLlegada) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (linea == null) {
			if (other.linea != null)
				return false;
		} else if (!linea.equals(other.linea))
			return false;
		if (proximo == null) {
			if (other.proximo != null)
				return false;
		} else if (!proximo.equals(other.proximo))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return linea + " Dirección: " + destino + " en " + proximo;
    }

    /**
     * @return the linea
     */
    public String getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the proximo
     */
    public String getProximo() {
        return proximo;
    }

    /**
     * @param proximo the proximo to set
     */
    public void setProximo(String proximo) {
        this.proximo = proximo;
    }

    public Integer getProximoMinutos() {
    	Integer minutos = 1000;
    	
    	if(proximo.contains("Sin estimacion")) {
    		minutos = 9999;
    	} else if(proximo.contains("En la parada")) {
    		minutos = 0;
    	} else {
    		Pattern p = Pattern.compile("([0-9]+) minutos.");
    		Matcher m = p.matcher(proximo);       
    		if(m.find()) {
    			minutos = new Integer(m.group(1));
    		}
    	}
    		
    	
    	return minutos;
    }



}
