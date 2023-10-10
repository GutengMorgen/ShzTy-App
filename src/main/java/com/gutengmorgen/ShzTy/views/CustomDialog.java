package com.gutengmorgen.ShzTy.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.services.GenreService;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	try {
	    CustomDialog dialog = new CustomDialog("Custom Dialog");
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.autoFill(DtoCreateAlbum.class);
	    dialog.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Create the dialog.
     */
    public CustomDialog(String title) {
	setTitle(title);
	setBounds(100, 100, 450, 300);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(new GridLayout(0, 2, 0, 0));

	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	    {
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
	    }
	}
    }

    public void autoFill(Class<?> classToRead) {
	Field[] fields = classToRead.getDeclaredFields();
	MainController controller = new MainController();

	for (Field field : fields) {
	    String fieldName = field.getName();

	    JLabel label = new JLabel(hasValidAnnotations(field, fieldName));
	    contentPanel.add(label);

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
		contentPanel.add(new JComboBox<>(model));

	    } else if (fieldName.contains("Date")) {
//		JTextField textField = new JTextField(10);
//		((AbstractDocument) textField.getDocument()).setDocumentFilter(new FilterDateFormat());
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		contentPanel.add(dateChooser);

	    } else if (fieldName.contains("IDs")) {
		// el controller devolvera una lista de objectos con dos parametros {nombre, id}
		contentPanel.add(controller.getGenreScrollList());
	    } else {
		JTextField textField = new JTextField(10);
		contentPanel.add(textField);
	    }
	}
    }

    private String hasValidAnnotations(Field field, String text) {
	if (field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(NotBlank.class))
	    return text + " *";
	else
	    return text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(okButton)) {
	    okAction();
	} else if (e.getSource().equals(cancelButton)) {
	    cancelAction();
	}
    }

    private void okAction() {
	// TODO Auto-generated method stub

    }

    private void cancelAction() {
	// TODO Auto-generated method stub

    }
}
