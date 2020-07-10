
public class Estacion {
	double temperatura=0;
	double viento=0;
	double humedad=0;
	
	public double getTemperatura() {
		return temperatura;
	}

	public double getViento() {
		return viento;
	}

	public double getHumedad() {
		return humedad;
	}

	//constructor vacio
	public Estacion(){
		
	}
	
	public void analizarMensaje(String msg){
		//saco la palabra medida
		msg=msg.substring(8,msg.length());
		
		//divido string
		String[] rta = msg.split(",");
		for(int i=0;i<rta.length;i++){
			if(rta[i].contains("%")){
				rta[i]=rta[i].substring(0,rta[i].length()-1);
				this.humedad=Double.parseDouble(rta[i]);
			}
			else if(rta[i].contains("m/s")){
				rta[i]=rta[i].substring(0,rta[i].length()-3);
				this.viento=Double.parseDouble(rta[i]);
			}
			else if(rta[i].contains("ºC")){
				rta[i]=rta[i].substring(0,rta[i].length()-2);
				this.temperatura=Double.parseDouble(rta[i]);
			}
		}
	}

	public void mostrarDatosCargados(){
		System.out.print(this.humedad+"\n");
		System.out.print(this.temperatura+"\n");
		System.out.print(this.viento+"\n");

	}
	
}
