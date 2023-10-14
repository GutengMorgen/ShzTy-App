package com.gutengmorgen.ShzTy.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoReturnArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.services.GenreService;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CustomDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private final JPanel cPanel = new JPanel();
    private final GridBagConstraints constraints = new GridBagConstraints();
    private JButton okButton;
    private JButton cancelButton;

    private int rowIndex = 0;

    public static void main(String[] args) {
	try {
	    CustomDialog dialog = new CustomDialog("Custom Dialog");
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CustomDialog(String title) {
	setTitle(title);
	setBounds(100, 100, 480, 175);

	cPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(cPanel, BorderLayout.WEST);
	GridBagLayout gbl_cPanel = new GridBagLayout();
	cPanel.setLayout(gbl_cPanel);

	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	    {
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			dispose();
		    }
		});
		buttonPane.add(cancelButton);
	    }
	}
    }

    public void autoFillV2(Object obj) {
	Class<?> class1 = obj.getClass();
	Field[] fields = class1.getDeclaredFields();

	for (Field field : fields) {
	    field.setAccessible(true);
	    
	    if (!field.isAnnotationPresent(ForGUI.class))
		break;

	    ForGUI forGUI = field.getAnnotation(ForGUI.class);
	    
	    try {
		String name = forGUI.name();
		Object value = field.get(obj);
		JComponent component = null;
		
		if (forGUI.type() == PropertieType.SIMPLE_TEXT) {
		    component = new JLabel(value.toString());
		} else if (forGUI.type() == PropertieType.SINGLE_OPTION) {
		    DefaultComboBoxModel<String> lm = new DefaultComboBoxModel<>();
		    lm.addAll((Collection<? extends String>) value);
		    component = new JComboBox<>(lm);
		}

		addComponent(name, component);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	closeAutoFill();
    }

    public Object autoFillMineV2(Class<?> cl) {
	Field[] fields = cl.getDeclaredFields();
	MainController controller = new MainController();
	
	for (Field field : fields) {
	    
	    field.setAccessible(true);
	    if(!field.isAnnotationPresent(ForGUI.class))
		break;
	    
	    ForGUI forGUI = field.getAnnotation(ForGUI.class);
	    
	    try {
		String name = forGUI.name();
		JComponent comp = null;
		
		if(forGUI.type() == PropertieType.SINGLE_OPTION) {
		    comp = controller.genreCB();
		}
		else if(forGUI.type() == PropertieType.MULTI_OPTION) {
		    comp = new JTextField();
		    comp.setToolTipText("para agregar mas optiones utilizar < ; >");
		}
		else {
		    comp = new JTextField(10);
		}
		
		addComponent(name, comp);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	closeAutoFill();
	return null;
    }
    
    public void autoFill(Class<?> classToRead) {
	Field[] fields = classToRead.getDeclaredFields();
	MainController controller = new MainController();
	for (Field field : fields) {
	    String fieldName = field.getName();

	    JLabel label = new JLabel(fieldName);
	    cPanel.add(label);

	    if (fieldName.contains("Id")) {
		// NOTE: fieldType.getSimpleName().equals("int")
//		JTextField textField = new JTextField(10);
//		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterOnlyNumbers());
//		contentPanel.add(textField);
		GenreService service = new GenreService();
		List<Genre> list = service.getAllGenres();
		DefaultComboBoxModel<Genre> model = new DefaultComboBoxModel<>();
		for (Genre genre : list) {
		    model.addElement(genre);
		}
		cPanel.add(new JComboBox<>(model));

	    } else if (fieldName.contains("Date")) {
//		JTextField textField = new JTextField(10);
//		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterDateFormat());
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		cPanel.add(dateChooser);

	    } else if (fieldName.contains("IDs")) {
		// el controller devolvera una lista de objectos con dos parametros {nombre, id}
		cPanel.add(controller.genreScrollPane());
	    } else {
		JTextField textField = new JTextField(10);
		cPanel.add(textField);
	    }
	}
    }
    
    public void autoFillReturn(DtoReturnArtist dto) {
	JLabel ll1 = new JLabel(dto.id().toString());
	addComponent("Id:", ll1);

	JLabel ll2 = new JLabel(dto.name());
	addComponent("Name:", ll2);

	JLabel ll3 = new JLabel(dto.bornDate().toString());
	addComponent("Born Date:", ll3);

	JLabel ll4 = new JLabel(dto.gender());
	addComponent("Gender:", ll4);

	JLabel ll5 = new JLabel(dto.country());
	addComponent("Country:", ll5);

	JLabel ll6 = new JLabel(dto.biography());
	addComponent("Biography:", ll6);

	DefaultComboBoxModel<String> ll7m = new DefaultComboBoxModel<>();
	ll7m.addAll(dto.albums());
	addComponent("Albums:", new JComboBox<>(ll7m));

	DefaultComboBoxModel<String> ll8m = new DefaultComboBoxModel<>();
	ll8m.addAll(dto.languages());
	addComponent("Languages:", new JComboBox<>(ll8m));

	DefaultComboBoxModel<String> ll9m = new DefaultComboBoxModel<>();
	ll9m.addAll(dto.genres());
	addComponent("Genres:", new JComboBox<>(ll9m));

	closeAutoFill();
    }

    private void addComponent(String name, JComponent comp) {
	constraints.gridx = 0;
	constraints.gridy = rowIndex;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.insets = new Insets(5, 5, 5, 5);
	constraints.fill = GridBagConstraints.BOTH;
	constraints.anchor = GridBagConstraints.WEST;

	cPanel.add(new JLabel(name), constraints);

	constraints.gridx = 1;
//	comp.setFocusable(false);
	cPanel.add(comp, constraints);

	rowIndex++;
    }

    private void closeAutoFill() {
	rowIndex = 0;
	cPanel.revalidate();
	cPanel.repaint();
	pack();

	okButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		dispose();
	    }
	});
    }
}
