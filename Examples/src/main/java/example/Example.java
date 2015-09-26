/*
 * Copyright 2015 Jonathan Keatley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example;

import spantable.Span;
import spantable.SpanTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Example extends JFrame {
    private JTextField rowField;
    private JTextField columnField;
    private JTextField heightField;
    private JTextField widthField;
    private MyTableModel tableModel = new MyTableModel();

    class AddSpanAction extends AbstractAction {
        AddSpanAction() {
            super("Add Span");
        }

        public void actionPerformed(ActionEvent e) {
            int row = Integer.valueOf(rowField.getText().trim());
            int column = Integer.valueOf(columnField.getText().trim());
            int height = Integer.valueOf(heightField.getText().trim());
            int width = Integer.valueOf(widthField.getText().trim());
            Span span = new Span(row, column, height, width);
            tableModel.getSpanModel().addSpan(span);
        }
    }

    class DeleteSpanAction extends AbstractAction {
        DeleteSpanAction() {
            super("Delete Span");
        }

        public void actionPerformed(ActionEvent e) {
            int row = Integer.valueOf(rowField.getText().trim());
            int column = Integer.valueOf(columnField.getText().trim());
            Span span = tableModel.getSpanModel().getDefinedSpan(row, column);
            tableModel.getSpanModel().removeSpan(span);
        }
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    Example example = new Example();
                    example.setVisible(true);
                }
            });
	}

    public Example() {
        super("Table with Spans");
        init();
    }

    private void init() {
        tableModel.addString("ABCDEF");
        tableModel.addString("GHIJKLM");
        tableModel.addString("NOPQ");
        tableModel.addString("RSTUVWXY");
        tableModel.addString("Z");
        SpanTable table = new SpanTable(tableModel);
        table.setDefaultRenderer(Boolean.class, new BooleanCellRenderer());
        table.setDefaultEditor(Boolean.class, new DefaultCellEditor(new JCheckBox()));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        getContentPane().add(createBottomPanel(), BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 295);
    }

    private JComponent createBottomPanel() {
        JComponent panel = Box.createVerticalBox();
        panel.add(createEntryPanel());
        panel.add(createButtonPanel());
        return panel;
    }

    private JComponent createEntryPanel() {
        JComponent panel = Box.createHorizontalBox();
        panel.add(Box.createHorizontalStrut(10));
        panel.add(new JLabel("Row:"));
        panel.add(Box.createHorizontalStrut(3));
        rowField = new JTextField();
        panel.add(rowField);
        panel.add(Box.createHorizontalGlue());
        panel.add(new JLabel("Column:"));
        panel.add(Box.createHorizontalStrut(3));
        columnField = new JTextField();
        panel.add(columnField);
        panel.add(Box.createHorizontalGlue());
        panel.add(new JLabel("Height:"));
        panel.add(Box.createHorizontalStrut(3));
        heightField = new JTextField();
        panel.add(heightField);
        panel.add(Box.createHorizontalGlue());
        panel.add(new JLabel("Width:"));
        panel.add(Box.createHorizontalStrut(3));
        widthField = new JTextField();
        panel.add(widthField);
        panel.add(Box.createHorizontalStrut(10));
        return panel;
    }

    private JComponent createButtonPanel() {
        JComponent panel = Box.createHorizontalBox();
        panel.add(Box.createHorizontalStrut(10));
        panel.add(Box.createHorizontalGlue());
        panel.add(new JButton(new AddSpanAction()));
        panel.add(Box.createHorizontalGlue());
        panel.add(new JButton(new DeleteSpanAction()));
        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalStrut(10));
        return panel;
    }
}

