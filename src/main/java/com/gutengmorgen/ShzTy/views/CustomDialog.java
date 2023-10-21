package com.gutengmorgen.ShzTy.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import org.hibernate.mapping.Component;
import org.hibernate.mapping.Value;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
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
    public JButton okButton;
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
//		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
//		getRootPane().setDefaultButton(okButton);
	    }
	    {
		cancelButton = new JButton("Cancel");
//		cancelButton.setActionCommand("Cancel");
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

    @SuppressWarnings("unchecked")
    public void autoFillReturn(Object obj) {
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

		if (forGUI.type() == GUIType.SIMPLE_TEXT) {
		    component = new JLabel(value.toString());
		} else if (forGUI.type() == GUIType.SINGLE_OPTION) {
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

    public void autoFillToInsert(Class<?> cl) {
	Field[] fields = cl.getDeclaredFields();
	MainController controller = new MainController();

	for (Field field : fields) {

	    field.setAccessible(true);
	    if (!field.isAnnotationPresent(ForGUI.class))
		break;

	    ForGUI forGUI = field.getAnnotation(ForGUI.class);

	    try {
		String name = forGUI.name();
		JComponent comp = null;

		if (forGUI.type() == GUIType.SINGLE_OPTION) {
		    comp = controller.textField(false);
		} else if (forGUI.type() == GUIType.MULTI_OPTION) {
		    comp = controller.textField(true);
		} else if (forGUI.type() == GUIType.DATE) {
		    comp = new CustomTextField(GUIType.DATE);
		} else {
		    comp = new CustomTextField(GUIType.SIMPLE_TEXT);
		}

		addComponent(name, comp);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
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
	if (comp instanceof JTextField)
	    comp.setPreferredSize(new Dimension(150, comp.getPreferredSize().height));
	cPanel.add(comp, constraints);

	rowIndex++;
    }

    private void closeAutoFill() {
	rowIndex = 0;
//	cPanel.revalidate();
//	cPanel.repaint();
	pack();
    }

    // NOTE: los fields y textos de los jtextfield ya estan ordenandos porque se
    // extraen de la misma clase
    public void getTypeTest() {
	java.awt.Component[] comps = cPanel.getComponents();
	Object type = null;
	List<Object> result = new ArrayList<>();

	for (java.awt.Component component : comps) {
	    if (component instanceof JTextField) {
		CustomTextField field = (CustomTextField) component;
		System.out.println(field.getType());
	    }
	}
    }

    public List<Object> getResult() {
	java.awt.Component[] comps = cPanel.getComponents();
	Object type = null;
	List<Object> result = new ArrayList<>();

	for (java.awt.Component component : comps) {
	    if (component instanceof JTextField) {
//		String text = ((JTextField) component).getText();
		CustomTextField field = (CustomTextField) component;
		GUIType typeField = field.getType();
		String textField = field.getText();

		if (typeField == GUIType.DATE) {
		    try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(format.parse(textField).getTime());
			type = date;
		    } catch (ParseException e) {
			e.printStackTrace();
		    }
		} else if (typeField == GUIType.MULTI_OPTION) {
		    Set<Long> longs = new HashSet<>();
		    if (textField.contains(",")) {
			String[] texts = textField.split(",");
			for (String obj : texts) {
			    Long long1 = findNameReturnLong(obj);
			    longs.add(long1);
			}
		    } else {
			longs.add(findNameReturnLong(textField));
		    }
		    type = longs;
		} else {
		    type = textField;
		}

		result.add(type);
	    }
	}
	return result;
    }

    private Long findNameReturnLong(String name) {
	return 1L;
    }
}
