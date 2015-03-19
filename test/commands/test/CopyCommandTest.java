package commands.test;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import commands.Command;
import commands.CopyCommand;
import editor.HTMLEditor;

public class CopyCommandTest {
	Command cc;
	JTextArea jta;
	HTMLEditor e;

	@Before
	public void setUp() throws Exception {
		jta = new JTextArea();
		e = new HTMLEditor();
		cc = new CopyCommand(jta, e);
	}

	@Test
	public final void testExecute() {
		jta.setText("hello world");
		jta.setSelectionStart(0);
		jta.setSelectionEnd(5);
		cc.execute();
		assertEquals(e.getClipboard(), "hello");
	}
}
