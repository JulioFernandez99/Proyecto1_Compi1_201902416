package main.metodoarbol;

public class Nodo {
	public Nodo hizq;
	public Nodo hder;
	public String valor;
	public int id;
	public int identificador;
	public String anulable;
	public String primero;
	public String ultimo;
	
	public Nodo(Nodo hizq, Nodo hder, String valor, int id, int identificador, String anulable, String primero,String ultimo) {
		super();
		this.hizq = hizq;
		this.hder = hder;
		this.valor = valor;
		this.id = id;
		this.identificador = identificador;
		this.anulable = anulable;
		this.primero = primero;
		this.ultimo = ultimo;
	}

	public Nodo getHizq() {
		return hizq;
	}

	public void setHizq(Nodo hizq) {
		this.hizq = hizq;
	}

	public Nodo getHder() {
		return hder;
	}

	public void setHder(Nodo hder) {
		this.hder = hder;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getAnulable() {
		return anulable;
	}

	public void setAnulable(String anulable) {
		this.anulable = anulable;
	}

	public String getprimero() {
		return primero;
	}

	public void setprimero(String primero) {
		this.primero = primero;
	}

	public String getUltimo() {
		return ultimo;
	}

	public void setUltimo(String ultimo) {
		this.ultimo = ultimo;
	}
	
	public String getCodigoInterno() {
		String etiqueta;
		if(hizq==null && hder==null) {
			etiqueta="nodo"+id+"[label=<\n"
				+ "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
				+ "<tr><td>"+anulable+"</td></tr>\n"
				+ "<tr><td>"+primero+"</td><td>"+valor+"</td><td>"+ultimo+"</td></tr>\n"
				+ "<tr><td>"+identificador+"</td></tr>\n"
				+ "</table>\n"
				+ ">];\n";
		}
		else {
			etiqueta="nodo"+id+"[label=<\n"
					+ "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
					+ "<tr><td>"+anulable+"</td></tr>\n"
					+ "<tr><td>"+primero+"</td><td>"+valor+"</td><td>"+ultimo+"</td></tr>\n"
					+ "<tr><td>"+identificador+"</td></tr>\n"
					+ "</table>\n"
					+ ">];\n";
			
		}
		if(hizq!=null) {
			etiqueta=etiqueta+hizq.getCodigoInterno()
				+"nodo"+id+"->nodo"+hizq.id+"\n";
		}
		if(hder!=null) {
			etiqueta=etiqueta+hder.getCodigoInterno()
				+"nodo"+id+"->nodo"+hder.id+"\n";	
		}
		return etiqueta;
	}
}
