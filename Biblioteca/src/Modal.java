import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

	public class Modal extends JFrame{
		
	// ***** A T R I B U T O S 
		private static final long serialVersionUID = 1L;
		JButton alta;
		JButton baja;
		JButton modificacion;
		JButton buscar;
		JButton listar;
		JTextArea detalle = new JTextArea("",10,20);
		ArrayList<Libro> listado = new ArrayList<Libro>();
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		DefaultListModel<String> dlm2 = new DefaultListModel<String>();
		JList<String> listado_libros = new JList<String>(dlm);
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		Bdd bdd = new Bdd();


	// *****  M E T O D O S 
		
		//VENTANA MODAL
		public Modal() throws IOException, FileNotFoundException, NullPointerException, ClassNotFoundException, SQLException {
			bdd.cargar();
			
			Color color = new Color(195, 215, 210);
			Font font = new Font("Consolas", Font.BOLD, 12);
			setTitle("Biblioteca <Nizza>");
			setSize(450,245);
			setResizable(false);
			p.setBackground(color);
			p2.setBackground(color);
			JScrollPane scrollPane1 = new JScrollPane(listado_libros);

			listado_libros.setVisibleRowCount(4);
			listado_libros.setFixedCellHeight(20);
			listado_libros.setFixedCellWidth(180);
			detalle.setLineWrap(true);
			detalle.setWrapStyleWord(true);
			
			//BOTON ALTA
			alta = new JButton("Nuevo");
			alta.setBounds(50, 50, 50, 50);
			alta.setFont(font);
			alta.setBackground(java.awt.Color.black);
			alta.setForeground(java.awt.Color.white);
			alta.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			try {
						crear();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
		    	}
			}); 
			
			//BOTON BAJA
			baja = new JButton("Eliminar");
			baja.setFont(font);
			baja.setBackground(java.awt.Color.black);
			baja.setForeground(java.awt.Color.white);
			baja.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			try {
						eliminar();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
		    	}
			}); 
			
			//BOTON MODIFICACION
			modificacion = new JButton("Editar");
			modificacion.setFont(font);
			modificacion.setForeground(java.awt.Color.white);
			modificacion.setBackground(java.awt.Color.black);
			modificacion.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			try {
	    				modificar();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
		    	}
			}); 

			//BOTON BUSCAR 
			buscar = new JButton("Buscar");
			buscar.setFont(font);
			buscar.setForeground(java.awt.Color.white);
			buscar.setBackground(java.awt.Color.black);
			buscar.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			try {
	    				buscar();
					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
		    	}
			}); 
			
			//BOTON LISTAR 
			listar = new JButton("Listar");
			listar.setFont(font);
			listar.setForeground(java.awt.Color.white);
			listar.setBackground(java.awt.Color.black);
			listar.addActionListener(new ActionListener(){ 
	    		public void actionPerformed(ActionEvent e){
	    			try {
	    				listar();
					} catch (NullPointerException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (ArrayIndexOutOfBoundsException e1) {
							e1.printStackTrace();
					}
		    	}
			}); 
		
			//LISTAR LIBROS 
			 cargar_listado();
	
			//APPEND DE ELEMENTOS
			p2.add(alta);
			p2.add(baja);
			p2.add(modificacion);
			p2.add(buscar);
			p2.add(listar);
			p.add(p2);
			p3.add(scrollPane1, BorderLayout.NORTH);
			p3.add(detalle);
			add(p,"North");
			add(p3,"South");
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		//LISTADO LIBROS
		private void cargar_listado() throws ClassNotFoundException, SQLException {
			bdd.conectar();
			dlm = new DefaultListModel<String>();
			listado = bdd.getLibros();
			for(int i = 0; i < listado.size(); i++) {
					dlm.addElement(listado.get(i).titulo);
			}
			listado_libros.setModel(dlm);

			listado_libros.addMouseListener(new MouseAdapter(){
		          @Override
		          public void mouseClicked(MouseEvent e) {
		              cargar_listado_detalle(listado_libros.getSelectedValue());
		          }
		    });
		}
		
		//DETALLE UN LIBRO
		private void cargar_listado_detalle(String x) {
			for(int i = 0;i<listado.size();i++) {
				if(listado.get(i).titulo.equals(x)) {
					if(detalle!=null) {
						detalle.selectAll();
						detalle.replaceSelection(
								"\n  Titulo: " + listado.get(i).titulo +
								"\n  Autor: " + listado.get(i).autor +
								"\n  Cantidad de paginas: " + listado.get(i).cant_paginas +
								"\n  ISBN: " + listado.get(i).ISBN +
							    "\n  Año: " + listado.get(i).anio +
							    "\n  Editorial: " + listado.get(i).editorial +
							    "\n  Genero: " + listado.get(i).genero + ""
						);
						detalle.repaint();
					}
					p3.revalidate();
					p3.repaint();
				}
			}
		}
		
		//BUSCAR 
		private void buscar() throws NullPointerException{
			String titulo = JOptionPane.showInputDialog(null,"Titulo: ");
			boolean no_encontrado=true;
			if(titulo!=null) {
				for(int i = 0;i<listado.size();i++) {
					if(listado.get(i).titulo.equals(titulo)) {
						listado_libros.setSelectedIndex(i);
						cargar_listado_detalle(listado.get(i).titulo);
						no_encontrado=false;
					}
				}
				if(no_encontrado) {
					JOptionPane.showMessageDialog(null, "No hay resultados de la busqueda \""+titulo+" \"", "Ups!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		
		//ELIMINAR
		private void eliminar() throws ClassNotFoundException, SQLException {
			if(listado_libros.getSelectedValue()!=null) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el libro "+listado.get(listado_libros.getSelectedIndex()).titulo+" ?");
				if(resp==0) {
					bdd.eliminar(listado_libros.getSelectedValue());
					cargar_listado();
					detalle.selectAll();
					detalle.replaceSelection("");
				}
			}
		}
		
		//CREAR
		private void crear() throws ClassNotFoundException, SQLException {
			JTextField titulo = new JTextField();
			JTextField autor = new JTextField();
			JTextField cant_paginas = new JTextField();
			JTextField ISBN = new JTextField();
			JTextField anio = new JTextField();
			JTextField editorial = new JTextField();
			JComboBox<String> genero = new JComboBox<String>();
			String[] un_genero = {"ficcion","accion","suspenso","romantico","novela","fantasia"};
			boolean ya_existe=false;
			
			for(int i=0;i<un_genero.length;i++) {
				genero.addItem(un_genero[i]);
			}
			
			Object[] fields = {
				"Titulo: ", titulo,
				"Autor: ", 	autor,
				"Cantidad de paginas: ", cant_paginas,
				"ISBN: ", 	ISBN,
				"Año: ", anio,
				"Editorial: ", editorial,
				"Genero: ", genero
			};
			
			int n = JOptionPane.showConfirmDialog(null, fields, "Alta de libro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE );
				if(n == JOptionPane.OK_OPTION){
					for(int i=0;i<listado.size();i++) {
						if(listado.get(i).titulo.equals(titulo.getText())) {
							ya_existe=true;
							JOptionPane.showMessageDialog(null, "El libro "+titulo.getText()+" ya forma parte de la biblioteca", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
					if(!ya_existe) {
						int cont=0;
						try {
							Integer.parseInt(anio.getText());
							cont++;
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "El año debe ser valido", "Error", JOptionPane.WARNING_MESSAGE);
						}
						try {
							Integer.parseInt(ISBN.getText());
							cont++;
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "El ISBN debe ser valido", "Error", JOptionPane.WARNING_MESSAGE);
						}
						try {
							Integer.parseInt(cant_paginas.getText());
							cont++;
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "La cantidad de paginas deben ser validas", "Error", JOptionPane.WARNING_MESSAGE);
						}

						if(cont==3) {
							if(!titulo.getText().equals("")&&
								   !autor.getText().equals("")&&
								   !cant_paginas.getText().equals("")&&
								   !ISBN.getText().equals("")&&
								   !anio.getText().equals("")&&
								   !editorial.getText().equals("")) {
									bdd.crear(titulo.getText(),autor.getText(),cant_paginas.getText(),ISBN.getText(),anio.getText(),editorial.getText(),genero.getSelectedItem().toString());
									cargar_listado();
							}
							else {
								JOptionPane.showMessageDialog(null, "Debe completar todos los datos del formulario", "Error", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
		}
		
		//MODIFICAR
		private void modificar() throws ClassNotFoundException, SQLException {
			if(listado_libros.getSelectedValue()!=null) {

				JTextField titulo = new JTextField();
				JTextField autor = new JTextField();
				JTextField cant_paginas = new JTextField();
				JTextField ISBN = new JTextField();
				JTextField anio = new JTextField();
				JTextField editorial = new JTextField();
				JComboBox<String> genero = new JComboBox<String>();
				String[] un_genero = {"ficcion","accion","suspenso","romantico","novela","fantasia"};
				String id="0";
				
				for(int i=0;i<un_genero.length;i++) {
					genero.addItem(un_genero[i]);
				}
				
				for(int i=0;i<listado.size();i++) {
					if(listado.get(i).titulo.equals(listado_libros.getSelectedValue())) {
						titulo.setText(listado.get(i).titulo);
						autor.setText(listado.get(i).autor);
						cant_paginas.setText(Integer.toString(listado.get(i).cant_paginas));
						ISBN.setText(Integer.toString(listado.get(i).ISBN));
						anio.setText(Integer.toString(listado.get(i).anio));
						editorial.setText(listado.get(i).editorial);
						genero.setSelectedItem(listado.get(i).genero);
						id=Integer.toString(listado.get(i).id);
					}
				}
				
				Object[] fields = {
					"Titulo: ", titulo,
					"Autor: ", 	autor,
					"Cantidad de paginas: ", cant_paginas,
					"ISBN: ", 	ISBN,
					"Año: ", anio,
					"Editorial: ", editorial,
					"Genero: ", genero
				};
				
				int n = JOptionPane.showConfirmDialog(null, fields, "Alta de libro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE );
					if(n == JOptionPane.OK_OPTION){
							int cont=0;
							try {
								Integer.parseInt(anio.getText());
								cont++;
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null, "El año debe ser valido", "Error", JOptionPane.WARNING_MESSAGE);
							}
							try {
								Integer.parseInt(ISBN.getText());
								cont++;
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null, "El ISBN debe ser valido", "Error", JOptionPane.WARNING_MESSAGE);
							}
							try {
								Integer.parseInt(cant_paginas.getText());
								cont++;
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null, "La cantidad de paginas deben ser validas", "Error", JOptionPane.WARNING_MESSAGE);
							}
	
							if(cont==3) {
								if(!titulo.getText().equals("")&&
									   !autor.getText().equals("")&&
									   !cant_paginas.getText().equals("")&&
									   !ISBN.getText().equals("")&&
									   !anio.getText().equals("")&&
									   !editorial.getText().equals("")) {
									
										bdd.modificar(id,titulo.getText(),autor.getText(),cant_paginas.getText(),ISBN.getText(),anio.getText(),editorial.getText(),genero.getSelectedItem().toString());
										cargar_listado();
										cargar_listado_detalle(titulo.getText());
										
								}
								else {
									JOptionPane.showMessageDialog(null, "Debe completar todos los datos del formulario", "Error", JOptionPane.WARNING_MESSAGE);
								}
							}
				}
			}
	}

		//LISTAR
		private void listar() throws NullPointerException, ClassNotFoundException, SQLException, ArrayIndexOutOfBoundsException {		
			DefaultListModel<String> dlm3 = new DefaultListModel<String>();
			JList<String> listado_filtros = new JList<String>(dlm3);
			/*1*/ dlm3.addElement("1.  Listar todos los autores existentes");
			/*2*/ dlm3.addElement("2.  Listar todos los libros existentes");
			/*3*/ dlm3.addElement("3.  Listar todos los libros de un género determinado");
			/*4*/ dlm3.addElement("4.  Listar todos los libros que posee un autor determinado");
			/*5*/ dlm3.addElement("5.  Listar todos los libros de una editorial determinada");
			/*6*/ dlm3.addElement("6.  Listar todos los libros de una editorial determinada en un rango de años de edición");
			/*7*/ dlm3.addElement("7.  Listar todos los autores de una determinada editorial");
			/*8*/ dlm3.addElement("8.  Listar todos los libros que fueron editados en un determinado año");
			/*9*/ dlm3.addElement("9.  Listar todos los libros de los autores cuyos apellidos comienzan con una letra determinada");
			/*10*/dlm3.addElement("10. Listar todos los libros cuyos títulos contengan una palabra determinada");
			listado_filtros.setModel(dlm3);
		    listado_filtros.setFixedCellHeight(30);
		    listado_filtros.setFixedCellWidth(650);
		 	final JPanel panel = new JPanel();
		 	panel.add(listado_filtros);
			panel.setPreferredSize(new Dimension(700, 350));
	        JOptionPane.showMessageDialog(null, panel );
	        if(listado_filtros.getSelectedValue()!=null) {
	        	String numero[] = listado_filtros.getSelectedValue().split("\\.");
	        	switch(numero[0]) {
	        		case "1": 
	        			listar_autores();
	        		break;
	        		case "2": 
	        			listar_libros();
		        	break;
	        		case "3": 
	        			listar_libros_por_genero();
		        	break;
		        	case "4": 
			        	libros_un_autor();	
			        break;
		        	case "5": 
			        	libros_una_editorial();	
		        	break;
		        	case "6": 
		        		libros_una_editorial_anio();
			        break;
		        	case "7": 
			        	autores_una_editorial();	
			        break;
			        case "8": 
			        	libros_por_anio();	
				    break;
		        	case "9": 
			        	autores_por_apellido();	
			        break;
			        case "10": 
				        libros_por_palabra();		
				    break;
	        	}
	        }
		}
		
		//LISTAR AUTORES
		private void listar_autores() {
			DefaultListModel<String> dlm4 = new DefaultListModel<String>();
			JList<String> listado_autores = new JList<String>(dlm4);
			JScrollPane scrollPane3 = new JScrollPane(listado_autores);
			listado_autores.setFixedCellHeight(30);
			listado_autores.setFixedCellWidth(150);
			listado_autores.setVisibleRowCount(4);
			for(int i=0;i<listado.size();i++) {
				dlm4.addElement(listado.get(i).autor);
			}
			listado_autores.setModel(dlm4);
			final JPanel panel = new JPanel();
		 	panel.add(scrollPane3);
			panel.setPreferredSize(new Dimension(200, 150));
	        JOptionPane.showMessageDialog(null, panel );
		}
		
		//LISTAR POR LIBROS
		private void listar_libros() {
			DefaultListModel<String> dlm4 = new DefaultListModel<String>();
			JList<String> listado_autores = new JList<String>(dlm4);
			JScrollPane scrollPane3 = new JScrollPane(listado_autores);
			listado_autores.setFixedCellHeight(30);
			listado_autores.setFixedCellWidth(150);
			listado_autores.setVisibleRowCount(4);
			for(int i=0;i<listado.size();i++) {
				dlm4.addElement(listado.get(i).titulo);
			}
			listado_autores.setModel(dlm4);
			final JPanel panel = new JPanel();
		 	panel.add(scrollPane3);
			panel.setPreferredSize(new Dimension(200, 150));
	        JOptionPane.showMessageDialog(null, panel );
		}
		
		//LISTAR POR GENERO
		private void listar_libros_por_genero() {
			JComboBox<String> genero = new JComboBox<String>();
			String[] un_genero = {"ficcion","accion","suspenso","romantico","novela","fantasia"};
			final JPanel panel = new JPanel();
			for(int i=0;i<un_genero.length;i++) {
				genero.addItem(un_genero[i]);
			}
			DefaultListModel<String> dlm4 = new DefaultListModel<String>();
			JList<String> listado_generos = new JList<String>(dlm4);
			JScrollPane scrollPane3 = new JScrollPane(listado_generos);
			listado_generos.setFixedCellHeight(30);
			listado_generos.setFixedCellWidth(150);
			listado_generos.setVisibleRowCount(4);
			//Accion
			genero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dlm4.clear();
					if(genero.getSelectedIndex()!=-1) {
						for(int i=0;i<listado.size();i++) {
							if(listado.get(i).genero.equals(genero.getSelectedItem())) {
								dlm4.addElement(listado.get(i).titulo);
							}
						}
						listado_generos.setModel(dlm4);
					}
				}
			});
			panel.add(genero);
			panel.add(scrollPane3);
			panel.setPreferredSize(new Dimension(200, 150));
			JOptionPane.showMessageDialog(null, panel);	
		}
		
		//LISTAR POR AUTOR
			private void libros_un_autor() {
				DefaultComboBoxModel <String> un_autor = new DefaultComboBoxModel <String>();
					final JPanel panel = new JPanel();
					un_autor.addElement(listado.get(0).autor);
					for(int i=0;i<listado.size();i++) {
						if(un_autor.getIndexOf(listado.get(i).autor)==-1){ 
							un_autor.addElement(listado.get(i).autor);
						}
					}
					JComboBox<String> autores = new JComboBox<String>(un_autor);
					DefaultListModel<String> dlm4 = new DefaultListModel<String>();
					JList<String> listado_libros = new JList<String>(dlm4);
					JScrollPane scrollPane3 = new JScrollPane(listado_libros);
					listado_libros.setFixedCellHeight(30);
					listado_libros.setFixedCellWidth(150);
					listado_libros.setVisibleRowCount(4);
					//Accion
					autores.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							dlm4.clear();
							if(autores.getSelectedIndex()!=-1) {
								for(int i=0;i<listado.size();i++) {
									if(listado.get(i).autor.equals(autores.getSelectedItem())) {
										dlm4.addElement(listado.get(i).titulo);
									}
								}
								listado_libros.setModel(dlm4);
							}
						}
					});
					panel.add(autores);
					panel.add(scrollPane3);
					panel.setPreferredSize(new Dimension(200, 150));
					JOptionPane.showMessageDialog(null, panel);	
		}
			
		//LISTAR POR EDITORIAL
			private void libros_una_editorial() {
				DefaultComboBoxModel <String> una_editorial = new DefaultComboBoxModel <String>();
					final JPanel panel = new JPanel();
					una_editorial.addElement(listado.get(0).editorial);
					for(int i=0;i<listado.size();i++) {
						if(una_editorial.getIndexOf(listado.get(i).editorial)==-1){ 
							una_editorial.addElement(listado.get(i).editorial);
						}
					}
					JComboBox<String> editorial = new JComboBox<String>(una_editorial);
					DefaultListModel<String> dlm4 = new DefaultListModel<String>();
					JList<String> listado_libros = new JList<String>(dlm4);
					JScrollPane scrollPane3 = new JScrollPane(listado_libros);
					listado_libros.setFixedCellHeight(30);
					listado_libros.setFixedCellWidth(150);
					listado_libros.setVisibleRowCount(4);
					//Accion
					editorial.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							dlm4.clear();
							if(editorial.getSelectedIndex()!=-1) {
								for(int i=0;i<listado.size();i++) {
									if(listado.get(i).editorial.equals(editorial.getSelectedItem())) {
										dlm4.addElement(listado.get(i).titulo);
									}
								}
								listado_libros.setModel(dlm4);
							}
						}
					});
					panel.add(editorial);
					panel.add(scrollPane3);
					panel.setPreferredSize(new Dimension(200, 150));
					JOptionPane.showMessageDialog(null, panel);	
		}
		
		//LISTAR POR EDITORIAL Y AÑO
			private void libros_una_editorial_anio() {
				DefaultComboBoxModel <String> una_editorial = new DefaultComboBoxModel <String>();
					una_editorial.addElement(listado.get(0).editorial);
					for(int i=0;i<listado.size();i++) {
						if(una_editorial.getIndexOf(listado.get(i).editorial)==-1){ 
							una_editorial.addElement(listado.get(i).editorial);
						}
					}
					JComboBox<String> editorial = new JComboBox<String>(una_editorial);
					JTextField anio_desde = new JTextField();
					anio_desde.setPreferredSize( new Dimension( 100, 26 ) );
					JTextField anio_hasta = new JTextField();
					anio_hasta.setPreferredSize( new Dimension( 100, 26 ) );

					Object[] fields = {
						"Editorial: ", editorial,
						"Desde: ", 	anio_desde,
						"Hasta: ", 	anio_hasta,
					};
					
					int n = JOptionPane.showConfirmDialog(null, fields, "Filtrar por editorial y año", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE );
					if(n == JOptionPane.OK_OPTION){
						try {
							final JPanel panel = new JPanel();
							DefaultListModel<String> dlm4 = new DefaultListModel<String>();
							JList<String> listado_libros = new JList<String>(dlm4);
							JScrollPane scrollPane3 = new JScrollPane(listado_libros);
							listado_libros.setFixedCellHeight(30);
							listado_libros.setFixedCellWidth(150);
							listado_libros.setVisibleRowCount(4);
							if(editorial.getSelectedIndex()!=-1) {
								for(int i=0;i<listado.size();i++) {
									if(listado.get(i).editorial.equals(editorial.getSelectedItem())) {
										if(Integer.parseInt(anio_desde.getText())<=listado.get(i).anio&&Integer.parseInt(anio_hasta.getText())>=listado.get(i).anio) { 
											dlm4.addElement(listado.get(i).titulo);
										}
									}
								}
								listado_libros.setModel(dlm4);
							}
							panel.add(scrollPane3);
							panel.setPreferredSize(new Dimension(200, 150));
							JOptionPane.showMessageDialog(null, panel);
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Debe ingresar un año valido");
						}
					}
		}
			
			//LISTAR AUTORES POR EDITORIAL
			private void autores_una_editorial() {
				DefaultComboBoxModel <String> una_editorial = new DefaultComboBoxModel <String>();
					final JPanel panel = new JPanel();
					una_editorial.addElement(listado.get(0).editorial);
					for(int i=0;i<listado.size();i++) {
						if(una_editorial.getIndexOf(listado.get(i).editorial)==-1){ 
							una_editorial.addElement(listado.get(i).editorial);
						}
					}
					JComboBox<String> editorial = new JComboBox<String>(una_editorial);
					DefaultListModel<String> dlm4 = new DefaultListModel<String>();
					JList<String> listado_libros = new JList<String>(dlm4);
					JScrollPane scrollPane3 = new JScrollPane(listado_libros);
					listado_libros.setFixedCellHeight(30);
					listado_libros.setFixedCellWidth(150);
					listado_libros.setVisibleRowCount(4);
					//Accion
					editorial.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							dlm4.clear();
							if(editorial.getSelectedIndex()!=-1) {
								for(int i=0;i<listado.size();i++) {
									if(listado.get(i).editorial.equals(editorial.getSelectedItem())) {
										dlm4.addElement(listado.get(i).autor);
									}
								}
								listado_libros.setModel(dlm4);
							}
						}
					});
					panel.add(editorial);
					panel.add(scrollPane3);
					panel.setPreferredSize(new Dimension(200, 150));
					JOptionPane.showMessageDialog(null, panel);	
		}
	
	//LISTAR POR AÑO
			private void libros_por_anio() {
				final JPanel panel = new JPanel();
				JTextField anio = new JTextField();
				anio.setPreferredSize( new Dimension( 100, 26 ) );
				DefaultListModel<String> dlm4 = new DefaultListModel<String>();
				JList<String> listado_libros = new JList<String>(dlm4);
				JScrollPane scrollPane3 = new JScrollPane(listado_libros);
				listado_libros.setFixedCellHeight(30);
				listado_libros.setFixedCellWidth(150);
				listado_libros.setVisibleRowCount(4);
					//Accion
					anio.getDocument().addDocumentListener(new DocumentListener() {
						  public void changedUpdate(DocumentEvent e) {
						    test();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  test();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  test();
						  }

						  public void test() {
						     if (anio.getText().length()==4){
								 dlm4.clear();
						    	 for(int i=0;i<listado.size();i++) {
										if(listado.get(i).anio==Integer.parseInt(anio.getText())) {
											dlm4.addElement(listado.get(i).titulo);
										}
									}
									listado_libros.setModel(dlm4);
						     }
						     else {
						    	 dlm4.clear();
						     }
						  }
						});
					
					panel.add(anio);
					panel.add(scrollPane3);
					panel.setPreferredSize(new Dimension(200, 150));
					JOptionPane.showMessageDialog(null, panel);	
		}
			
	//LISTAR POR LETRA DE APELLIDO
			private void autores_por_apellido() {
				final JPanel panel = new JPanel();
				JTextField letra = new JTextField();
				letra.setPreferredSize( new Dimension( 100, 26 ) );
				DefaultListModel<String> dlm4 = new DefaultListModel<String>();
				JList<String> listado_autores = new JList<String>(dlm4);
				JScrollPane scrollPane3 = new JScrollPane(listado_autores);
				listado_autores.setFixedCellHeight(30);
				listado_autores.setFixedCellWidth(150);
				listado_autores.setVisibleRowCount(4);
				//Accion
				letra.getDocument().addDocumentListener(new DocumentListener() {
						  public void changedUpdate(DocumentEvent e) {
						    test();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  test();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  test();
						  }
						  public void test() {
						     if (letra.getText().length()==1){
								 dlm4.clear();
						    	 for(int i=0;i<listado.size();i++) {
						    		String apellido[] = listado.get(i).autor.split(" ");
							    	if(apellido[1].toLowerCase().contains(letra.getText().toLowerCase())) {
							    		dlm4.addElement(listado.get(i).autor);
							    	}
								}
						    	 listado_autores.setModel(dlm4);
						     }
						     else {
						    	 dlm4.clear();
						     }
						  }
					});
					panel.add(letra);
					panel.add(scrollPane3);
					panel.setPreferredSize(new Dimension(200, 150));
					JOptionPane.showMessageDialog(null, panel);	
		}
			
	//LISTAR POR LETRA PALABRA
			private void libros_por_palabra() {
				final JPanel panel = new JPanel();
				JTextField palabra = new JTextField();
				palabra.setPreferredSize( new Dimension( 100, 26 ) );
				DefaultListModel<String> dlm4 = new DefaultListModel<String>();
				JList<String> listado_libros = new JList<String>(dlm4);
				JScrollPane scrollPane3 = new JScrollPane(listado_libros);
				listado_libros.setFixedCellHeight(30);
				listado_libros.setFixedCellWidth(150);
				listado_libros.setVisibleRowCount(4);
				//Accion
				palabra.getDocument().addDocumentListener(new DocumentListener() {
						  public void changedUpdate(DocumentEvent e) {
						    test();
						  }
						  public void removeUpdate(DocumentEvent e) {
							  test();
						  }
						  public void insertUpdate(DocumentEvent e) {
							  test();
						  }
						  public void test() {
						     if (palabra.getText().length()>=2){
								 dlm4.clear();
						    	 for(int i=0;i<listado.size();i++) {
							    	if(listado.get(i).titulo.toLowerCase().contains(palabra.getText().toLowerCase())) {
							    		dlm4.addElement(listado.get(i).titulo);
							    	}
								}
						    	 listado_libros.setModel(dlm4);
						     }
						     else {
						    	 dlm4.clear();
						     }
						  }
					});
					panel.add(palabra);
					panel.add(scrollPane3);
					panel.setPreferredSize(new Dimension(200, 150));
					JOptionPane.showMessageDialog(null, panel);	
		}
								
}
	
	
	
