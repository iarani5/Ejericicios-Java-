import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

	public class Modal extends JFrame{
		
		// ***** A T R I B U T O S  ***** //
		private static final long serialVersionUID = 1L;
		JButton atras;
		JButton adelante;
		JButton calcular;
		JTextField input_precio;
		JTextField input_cantidad;
		JTextField total_pagar;
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		DefaultListModel<String> dlm2 = new DefaultListModel<String>();
		JList<String> tInforme = new JList<String>(dlm);
		JList<String> lPedido = new JList<String>();
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		ArrayList <String> cTipoProductos;
		Supermercado supermercado = new Supermercado();


		
		// *****  M E T O D O S  ***** //
		public Modal() throws IOException, FileNotFoundException {
			
			//estilos de la ventana
			setTitle("Compra Virtual <Nizza>");
			setSize(450,200);
			p.setBackground(Color.pink);
			p2.setBackground(Color.pink);
			JScrollPane scrollPane1 = new JScrollPane(tInforme);
			JScrollPane scrollPane2 = new JScrollPane(lPedido);
			tInforme.setVisibleRowCount(5);
			tInforme.setFixedCellHeight(12);
			tInforme.setFixedCellWidth(100);
		    lPedido.setVisibleRowCount(5);
		    lPedido.setFixedCellHeight(12);
		    lPedido.setFixedCellWidth(100);
		    
			//BOTON ELIMINAR
			atras = new JButton("<");
			atras.setBackground(java.awt.Color.black);
			atras.setForeground(java.awt.Color.pink);
			atras.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			eliminar_item_seleccionado();
		    	}
			}); 
			
			//BOTON INGRESAR
			adelante = new JButton(">");
			adelante.setBackground(java.awt.Color.black);
			adelante.setForeground(java.awt.Color.pink);
			adelante.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			cargar_item_seleccionado();
		    	}
			}); 
			
			//BOTON CALCULAR
			calcular = new JButton("Calcular");
			calcular.setForeground(java.awt.Color.pink);
			calcular.setBackground(java.awt.Color.black);
			calcular.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			calcular_precio();
		    	}
			}); 

			//INPUT $
			JTextField input_precio_signo = new JTextField("$");
			input_precio_signo.setEditable(false);
			input_precio_signo.setPreferredSize( new Dimension( 20, 26 ) );
			
			//INPUT PRECIO
			input_precio = new JTextField("0.00");
			input_precio.setEditable(false); 
			input_precio.setPreferredSize( new Dimension( 60, 26 ) );
			
			//INPUT CANTIDAD
			input_cantidad = new JTextField();
			input_cantidad.setPreferredSize( new Dimension( 40, 25 ) );
			
			//INPUT TOTAL A PAGAR
			total_pagar = new JTextField("Total a pagar");
			total_pagar.setEditable(false); 
			total_pagar.setPreferredSize( new Dimension( 200, 26 ) );

			//SELECT TIPO PRODUCTO
			JComboBox<String> lProductos = new JComboBox<String>();
			cTipoProductos = new ArrayList<String>();
			cTipoProductos.add(supermercado.un_super.get(0).tipo);
			for(int i = 0; i < supermercado.un_super.size(); i++) {
				boolean ya_existe = false;
				for(int j=0;j<cTipoProductos.size();j++) {
					if(cTipoProductos.get(j).equals(supermercado.un_super.get(i).tipo)) {
						ya_existe = true;
					}
				}
				if(!ya_existe) {
					cTipoProductos.add(supermercado.un_super.get(i).tipo);
				}
			}
			for(int j=0;j<cTipoProductos.size();j++) {
				lProductos.addItem(cTipoProductos.get(j));
			}
			
			//Accion
			lProductos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cargar_subitems(lProductos.getSelectedItem().toString());
				}
			});
			
			//LIST SUBITEMS (por primera vez)
			 cargar_subitems(supermercado.un_super.get(0).tipo);
	
			//APPEND DE ELEMENTOS
			p.add(lProductos);
			p.add(scrollPane1, BorderLayout.NORTH);
			p2.add(adelante);
			p2.add(atras);
			p2.add(input_cantidad);
			p2.setPreferredSize( new Dimension( 70, 95 ) );
			p.add(p2);
			p.add(scrollPane2, BorderLayout.NORTH);
			p3.add(total_pagar);
			p3.add(calcular);
			p3.add(input_precio_signo);
			p3.add(input_precio);
			add(p,"North");
			add(p3,"South");
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		private void cargar_subitems(String producto) {
			dlm = new DefaultListModel<String>();
			for(int i = 0; i < supermercado.un_super.size(); i++) {
				if(producto.equals(supermercado.un_super.get(i).tipo)) {
					dlm.addElement(supermercado.un_super.get(i).desc);
				}
			}
			tInforme.setModel(dlm);
		}
		
		private void cargar_item_seleccionado() {
			if(tInforme.getSelectedValue()!=null) {
				for(int i=0;i<supermercado.un_super.size();i++) {
					if(supermercado.un_super.get(i).desc.equals(tInforme.getSelectedValue())) {
						int cant=0;
						if(input_cantidad.getText().length()>0) {
							try{
								cant=Integer.parseInt(input_cantidad.getText());
							}
							catch(NumberFormatException e){
								JOptionPane.showMessageDialog(null, "Error, debe ingresar solo numeros.");
							}
						}
						else {
							cant=1;
						}
						
						if(cant!=0) {
							int num=supermercado.un_super.get(i).restar_cantidad(cant);
							if(num<0) {
								if((cant+num)==0) {
									JOptionPane.showMessageDialog(null, "Ups! Ya no quedan unidades de "+supermercado.un_super.get(i).desc);
									supermercado.un_super.get(i).sumar_cantidad(cant);
								}
								else {
									supermercado.un_super.get(i).sumar_cantidad(cant);
									JOptionPane.showMessageDialog(null, "Ups! Solo quedan " +(cant+num)+ " unidades de "+supermercado.un_super.get(i).desc);
								}
							}
							else {
								dlm2.addElement(supermercado.un_super.get(i).desc+":"+cant);
								lPedido.setModel(dlm2);
							}
						}
					}
				}
			}
			lPedido.setModel(dlm2);
	}
		

	private void eliminar_item_seleccionado() {
		 int index = lPedido.getSelectedIndex();
		 if(index >= 0){
			 for(int i = 0; i < lPedido.getModel().getSize(); i++) {
				 if(index==i) {
					 lPedido.getModel().getElementAt(i);
					 String[] texto = lPedido.getModel().getElementAt(i).toString().split(":");
					 for(int j = 0; j < supermercado.un_super.size(); j++) {
						if(texto[0].toString().equals(supermercado.un_super.get(j).desc)) {
							 supermercado.un_super.get(j).sumar_cantidad(Integer.parseInt(texto[1]));
						}
					 }
				 }
			 }
		     dlm2.removeElementAt(index);
		 }
	}
	
	private void calcular_precio() {
		double cantidad=0;
		 for(int i = 0; i < lPedido.getModel().getSize(); i++) {
			 String[] texto = lPedido.getModel().getElementAt(i).toString().split(":");
			 for(int j = 0; j < supermercado.un_super.size(); j++) {
				 if(texto[0].toString().equals(supermercado.un_super.get(j).desc)) {
					 cantidad+=supermercado.un_super.get(j).precio*Integer.parseInt(texto[1]);
				 }
			 }
		 }
		 if(cantidad>1000) {
			 input_precio.setBackground(java.awt.Color.red);
		 }
		 else{
			 input_precio.setBackground(java.awt.Color.green);
		 }
		 input_precio.setText(String.valueOf(new DecimalFormat("##.##").format(cantidad)));
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
