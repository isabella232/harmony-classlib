/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.accessibility.AccessibleContext;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledEditorKit;
//import javax.swing.text.html.HTMLEditorKit;
//import javax.swing.text.rtf.RTFEditorKit;

public class JEditorPaneTest extends SwingTestCase {
    JEditorPane         jep;

    JEditorPane         jep1;

    JFrame              jf;

    String              fireOrder;

    static final String plainString = "justPlain";

    static final String rtfString   = "{\\rtf1\\ansi\\ansicpg1251\\deff0\\deflang1049{\\fonttbl{\\fswiss\\fcharset0 Arial;}}\n"
                                            + "{\\*\\generator Msftedit 5.41.15.1503;}\\viewkind4\\uc1\\pard\\lang1033\\f0\\fs20 blablabla\\par\n"
                                            + "}\n";

    static final String htmlString  =  "<html>"
                                            + "  <head>"
                                            + "    <meta http-equiv=\"Content-Typecontent=text/html;\" charset=\"iso-8859-1\">"
                                            + "    <meta content=\"MSHTML5.50.4522.1800\" name=\"GENERATOR\">"
                                            + "    "
                                            + "  </head>"
                                            + "  <body>"
                                            + "    <div bgcolor=\"#ffffff\">"
                                            + "      <div>"
                                            + "        <font size=\"4\">Thank you for the quote you sentreguarding account"
                                            + "         #99999999999999. &amp;160;I just have a couple of questions.Please let me"
                                            + "         know. </font>"
                                            + "      </div>"
                                            + "      <div>"
                                            + "        <font size=\"4\">&#160;&#160;&#160;&#160;Thank you, we look forward to hearing from you.</font>"
                                            + "      </div>" + "    </div>"
                                            + "  </body>" + "</html>";

    final URL           TEST_URL;
    {
        URL tmpUrl = null;
        try {
            tmpUrl = new URL("http://www.apache.org/");
        } catch (final MalformedURLException e) {
            assertFalse("unexpected exception :" + e.getMessage(), true);
        }
        TEST_URL = tmpUrl;
    }

    protected void setUp() throws Exception {
        super.setUp();
        timeoutDelay = 10 * DEFAULT_TIMEOUT_DELAY;
        setIgnoreNotImplemented(true);
        fireOrder = "";
        jep = new JEditorPane();
        jep1 = new JEditorPane();
        jf = new JFrame();
        Container c = jf.getContentPane();
        c.setLayout(new GridLayout(2, 1, 1, 1));
        c.add(new JScrollPane(jep1));
        c.add(jep);
        jf.setSize(200, 300);
    }

    protected void tearDown() throws Exception {
        jf.dispose();
        super.tearDown();
    }

    final class SimpleHyperlinkListener implements HyperlinkListener {
        String         name;

        HyperlinkEvent event;

        public SimpleHyperlinkListener(final String s) {
            name = s;
        }

        public void hyperlinkUpdate(final HyperlinkEvent he) {
            event = he;
            fireOrder += name;
        }
    }

    static final class SimpleEditorKit extends DefaultEditorKit {
        boolean wasCallInstall   = false;

        boolean wasCallDeinstall = false;

        public void deinstall(final JEditorPane jep) {
            wasCallDeinstall = true;
            super.deinstall(jep);
        }

        public void install(final JEditorPane jep) {
            wasCallInstall = true;
            super.install(jep);
        }

        final void resetDbgInfo() {
            wasCallInstall = false;
            wasCallDeinstall = false;
        }
    }

    final class SimplePropertyChangeListener implements PropertyChangeListener {
        PropertyChangeEvent event;

        public void propertyChange(final PropertyChangeEvent pce) {
            //System.out.println(pce.getPropertyName());
            event = pce;
        }

        final void resetDbgInfo() {
            event = null;
        }
    }

    final class SimpleClassLoader extends ClassLoader {
        boolean wasCallLoadClass = false;

        public Class loadClass(final String s) throws ClassNotFoundException {
            wasCallLoadClass = true;
            return super.loadClass(s);
        }

        protected synchronized Class loadClass(final String s, final boolean b)
                throws ClassNotFoundException {
            wasCallLoadClass = true;
            return super.loadClass(s, b);
        }
    }

    private void assertEquals(final PropertyChangeEvent event1,
                              final PropertyChangeEvent event2) {
        assertNotNull(event1);
        assertNotNull(event2);
        assertEquals(event1.getPropertyName(), event2.getPropertyName());
        assertEquals(event1.getOldValue(), event2.getOldValue());
        assertEquals(event1.getNewValue(), event2.getNewValue());
        assertEquals(event1.getSource(), event2.getSource());
    }

    public void testGetAccessibleContext() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        if (isHarmony()) {
            AccessibleContext accessibleContext = jep.getAccessibleContext();
            assertEquals("javax.swing.JEditorPane$AccessibleJEditorPane",
                     getClassName(accessibleContext));
            assertEquals(jep.getAccessibleContext(), accessibleContext);
                jep.setContentType("text/html");
            assertEquals("javax.swing.JEditorPane$AccessibleJEditorPaneHTML",
                        getClassName(jep.getAccessibleContext()));
        }
    }

    public void testGetPreferredSize() {
        if (isHarmony()) {
            jep1.setMinimumSize(new Dimension(3,3));
            JViewport parent = (JViewport)jep1.getParent();
            Dimension minSize = jep1.getMinimumSize();
            parent.setSize(new Dimension(minSize.width - 1, minSize.height - 1));
            assertEquals(minSize, jep1.getPreferredSize());
        }
    }

    public void testGetUIClassID() {
        assertEquals("EditorPaneUI", jep.getUIClassID());
    }

    public void testGetScrollableTracksViewportWidthHeight() {
        JViewport parent = (JViewport)jep1.getParent();
        jep1.setMinimumSize(new Dimension(3,3));
        Dimension minSize = jep1.getMinimumSize();
        parent.setSize(new Dimension(minSize.width - 1, minSize.height - 1));
        assertFalse(jep1.getScrollableTracksViewportHeight());
        assertFalse(jep1.getScrollableTracksViewportWidth());
    }

    private AttributeSet getAttributeSetByIndex(final AbstractDocument d,
                                                final int offset) {
        AttributeSet as = null;
        Element elem = d.getDefaultRootElement();
        while (elem.getElementCount() > 0) {
            elem = elem.getElement(elem.getElementIndex(offset));
            as = elem.getAttributes();
        }
        return as;
    }

    public void testReplaceSelection() {
        jep.setText("testReplaceSelection");
        jep.setSelectionStart(4);
        jep.setSelectionEnd(7);

        jep.replaceSelection("XXX");
        assertEquals("testXXXlaceSelection", jep.getText());
        assertNull(jep.getSelectedText());

        jep.setSelectionStart(2);
        jep.setSelectionEnd(4);
        jep.replaceSelection(null);
        assertEquals("teXXXlaceSelection", jep.getText());
        assertNull(jep.getSelectedText());

        jep.setSelectionStart(0);
        jep.setSelectionEnd(2);
        jep.replaceSelection(null);
        assertEquals("XXXlaceSelection", jep.getText());
        assertNull(jep.getSelectedText());

        jep.setCaretPosition(3);
        jep.replaceSelection("YYY");
        assertEquals("XXXYYYlaceSelection", jep.getText());
        assertNull(jep.getSelectedText());
    }

    public void testReplaceSelectionNotEditable() {
        jep.setText("replaceSelectionNotEditable");
        jep.setEditable(false);
        jep.setSelectionStart(3);
        jep.setSelectionEnd(5);
        jep.replaceSelection("YYY");
        assertEquals("la", jep.getSelectedText());
        assertEquals("replaceSelectionNotEditable", jep.getText());
    }

    public void testReplaceSelectionWithAttributes() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        AbstractDocument doc = new DefaultStyledDocument();
        SimpleAttributeSet as1 = new SimpleAttributeSet();
        as1.addAttribute("key1", "value1");
        SimpleAttributeSet as2 = new SimpleAttributeSet();
        as2.addAttribute("key2", "value2");

        try {
            doc.insertString(0, "testReplaceSelection", as1);
            doc.insertString(4, "INSERT", as2);
        } catch (final BadLocationException e) {
            assertFalse("unexpected exception :" + e.getMessage(), true);
        }
        //temporarily commented-out: HTMLEditorKit not implemented
        //jep.setEditorKit(new RTFEditorKit());
        jep.setEditorKit(new StyledEditorKit());
        jep.setDocument(doc);

        jep.setSelectionStart(6);
        jep.setSelectionEnd(7);
        jep.replaceSelection("YYY");

        for (int i = 0; i < doc.getLength(); i++) {
            AttributeSet as = getAttributeSetByIndex(doc, i);
            if (i > 3 && i < 12)
                assertEquals(as2, as);
            else
                assertEquals(as1, as);
        }
    }

    static final String getDocContent(final Document doc) {
        String content = null;
        try {
            content = doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
        }
        return content;
    }

    public void testSetGetText() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        jep.setText(plainString);
        assertEquals("plain", plainString, jep.getText());

        jep.setContentType("text/rtf");

        jep.setText(rtfString);
        assertEquals("blablabla\n", getDocContent(jep.getDocument()));

        jep.setContentType("text/html");
        jep.setText(htmlString);
        assertEquals(removeMeta(htmlString), removeMeta(jep.getText()
                .replaceAll("\n", "")));
    }
    // commented due to unimplemented functionality
    /*public void testCreateEditorKitForContentType() {
        String content = "testCreateEditorKitForContentType";
        assertNull(JEditorPane.createEditorKitForContentType(content));
        JEditorPane
                .registerEditorKitForContentType(content,
                                                 "javax.swing.text.DefaultEditorKit");
        EditorKit rtfKit1 = JEditorPane
                .createEditorKitForContentType("text/rtf");
        EditorKit rtfKit2 = JEditorPane
                .createEditorKitForContentType("text/rtf");
        EditorKit htmlKit1 = JEditorPane
                .createEditorKitForContentType("text/html");
        EditorKit htmlKit2 = JEditorPane
                .createEditorKitForContentType("text/html");
        EditorKit plainKit1 = JEditorPane
                .createEditorKitForContentType("text/plain");

        EditorKit plainKit2 = JEditorPane
                .createEditorKitForContentType("text/plain");
        EditorKit contentKit1 = JEditorPane
                .createEditorKitForContentType(content);
        EditorKit contentKit2 = JEditorPane
                .createEditorKitForContentType(content);

        assertEquals("javax.swing.text.rtf.RTFEditorKit", getClassName(rtfKit1));
        assertEquals("javax.swing.text.html.HTMLEditorKit",
                     getClassName(htmlKit1));
        assertEquals("javax.swing.JEditorPane$PlainEditorKit",
                    getClassName(plainKit1));
        assertEquals("javax.swing.text.DefaultEditorKit",
                     getClassName(contentKit1));

        assertNotSame(rtfKit1, rtfKit2);
        assertNotSame(htmlKit1, htmlKit2);
        assertNotSame(plainKit1, plainKit2);
        assertNotSame(contentKit1, contentKit2);
    }*/

    public void testRegisterEditorKitForContentTypeStringString() {
        JEditorPane
                .registerEditorKitForContentType("text/test1",
                                                 "javax.swing.text.DefaultEditorKit");
        assertEquals("javax.swing.text.DefaultEditorKit", JEditorPane
                .getEditorKitClassNameForContentType("text/test1"));

        JEditorPane.registerEditorKitForContentType("text/test1", "");

        JEditorPane
                .registerEditorKitForContentType("text/test1",
                                                 "javax.swing.JEditorPaneTest$SimpleEditorKit");
        assertEquals("javax.swing.JEditorPaneTest$SimpleEditorKit", JEditorPane
                .getEditorKitClassNameForContentType("text/test1"));
    }

    public void testRegisterEditorKitForContentTypeStringStringClassLoader() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        SimpleClassLoader cl = new SimpleClassLoader();

        cl.wasCallLoadClass = false;
        JEditorPane
                .registerEditorKitForContentType(
                                                 "content1",
                                                 "javax.swing.text.DefaultEditorKit",
                                                 cl);
        JEditorPane
                .registerEditorKitForContentType("content2",
                                                 "javax.swing.text.DefaultEditorKit");
        assertFalse(cl.wasCallLoadClass);

        jep.setContentType("content1");
        assertTrue(cl.wasCallLoadClass);

        cl.wasCallLoadClass = false;
        jep.setContentType("content2");
        assertFalse(cl.wasCallLoadClass);
    }

    private void checkBaseValues(final String docClass, final String kitClass,
                                 final String contentType,
                                 final URL currentPage, final JEditorPane c) {
        assertEquals(docClass, getClassName(c.getDocument()));
        assertEquals(kitClass, getClassName(c.getEditorKit()));
        assertEquals(contentType, c.getContentType());
        assertEquals(currentPage, c.getPage());

    }

    public void testJEditorPane() {
        checkBaseValues("javax.swing.text.PlainDocument",
                        "javax.swing.JEditorPane$PlainEditorKit", "text/plain",
                        null, jep);
    }

    public void testJEditorPaneString() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        try {
            jep = new JEditorPane("http://www.apache.org/");
        } catch (IOException e) {
            assertFalse("Unexpected exception: " + e.getMessage(), true);
        }
        checkBaseValues("javax.swing.text.html.HTMLDocument",
                        "javax.swing.text.html.HTMLEditorKit", "text/html",
                        TEST_URL, jep);
    }

    public void testJEditorPaneStringString() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        jep = new JEditorPane("text/html", htmlString);
        checkBaseValues("javax.swing.text.html.HTMLDocument",
                        "javax.swing.text.html.HTMLEditorKit", "text/html",
                        null, jep);
        assertEquals(removeMeta(htmlString), removeMeta(jep.getText()
                                                        .replaceAll("\n", "")));
    }

    public void testJEditorPaneURL() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        try {
            jep = new JEditorPane(TEST_URL);
        } catch (IOException e) {
            assertFalse("Unexpected exception: " + e.getMessage(), true);
        }
        checkBaseValues("javax.swing.text.html.HTMLDocument",
                        "javax.swing.text.html.HTMLEditorKit", "text/html",
                        TEST_URL, jep);
    }

    private void assertEquals(final ArrayList a, final Object objects[]) {
        int size = a.size();
        assertEquals(size, objects.length);
        for (int i = 0; i < size; i++) {
            assertEquals(a.get(i), objects[i]);
        }
    }

    public void testAddRemoveGetHyperlinkListener() {
        HyperlinkListener listener1 = new SimpleHyperlinkListener("1");
        HyperlinkListener listener2 = new SimpleHyperlinkListener("2");
        HyperlinkListener listener3 = new SimpleHyperlinkListener("3");
        HyperlinkListener listeners[];
        ArrayList testList = new ArrayList();

        jep.addHyperlinkListener(listener1);
        listeners = jep.getHyperlinkListeners();
        testList.add(listener1);
        assertEquals(testList, listeners);

        jep.addHyperlinkListener(listener2);
        listeners = jep.getHyperlinkListeners();
        testList.add(0, listener2);
        assertEquals(testList, listeners);

        jep.addHyperlinkListener(listener3);
        listeners = jep.getHyperlinkListeners();
        testList.add(0, listener3);
        assertEquals(testList, listeners);

        jep.addHyperlinkListener(listener1);
        listeners = jep.getHyperlinkListeners();
        testList.add(0, listener1);
        assertEquals(testList, listeners);

        jep.removeHyperlinkListener(listener2);
        listeners = jep.getHyperlinkListeners();
        testList.remove(listener2);
        assertEquals(testList, listeners);

        jep.removeHyperlinkListener(listener1);
        listeners = jep.getHyperlinkListeners();
        testList.remove(listener1);
        assertEquals(testList, listeners);

        jep.removeHyperlinkListener(listener3);
        listeners = jep.getHyperlinkListeners();
        testList.remove(listener3);
        assertEquals(testList, listeners);

        jep.removeHyperlinkListener(listener1);
        listeners = jep.getHyperlinkListeners();
        testList.remove(listener1);
        assertEquals(testList, listeners);
    }

    public void testCreateDefaultEditorKit() {
        EditorKit kit1 = jep.createDefaultEditorKit();
        EditorKit kit2 = jep.createDefaultEditorKit();
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", kit1.getClass()
                .getName());
        assertNotSame(kit1, kit2);
    }

    private HyperlinkEvent getHyperlinkEvent(final HyperlinkEvent.EventType type) {
        return new HyperlinkEvent(jep, type, TEST_URL);
    }

    public void testFireHyperlinkUpdate() {
        SimpleHyperlinkListener listener1 = new SimpleHyperlinkListener("1");
        SimpleHyperlinkListener listener2 = new SimpleHyperlinkListener("2");
        SimpleHyperlinkListener listener3 = new SimpleHyperlinkListener("3");
        jep.addHyperlinkListener(listener1);
        jep.addHyperlinkListener(listener2);
        jep.addHyperlinkListener(listener3);

        HyperlinkEvent event = getHyperlinkEvent(HyperlinkEvent.EventType.ACTIVATED);
        jep.fireHyperlinkUpdate(event);
        assertEquals("321", fireOrder);
        assertEquals(event, listener1.event);
        assertEquals(event, listener2.event);
        assertEquals(event, listener3.event);

        fireOrder = "";
        event = getHyperlinkEvent(HyperlinkEvent.EventType.EXITED);
        jep.fireHyperlinkUpdate(event);
        assertEquals("321", fireOrder);
        assertEquals(event, listener1.event);
        assertEquals(event, listener2.event);
        assertEquals(event, listener3.event);

        fireOrder = "";
        event = getHyperlinkEvent(HyperlinkEvent.EventType.ENTERED);
        jep.fireHyperlinkUpdate(event);
        assertEquals("321", fireOrder);
        assertEquals(event, listener1.event);
        assertEquals(event, listener2.event);
        assertEquals(event, listener3.event);

    }

    public void testSetGetPage1() {
        SimplePropertyChangeListener listener = new SimplePropertyChangeListener();
        jep.addPropertyChangeListener(listener);
        assertNull(jep.getPage());
        try {
            jep.setPage(TEST_URL);
        } catch (final IOException e) {
            assertTrue("Unexpected exception :" + e.getMessage(), false);
        }
        //assertEquals(TEST_URL, jep.getPage());
    }

    public void testSetGetPage2() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        String testUrlString = "http://www.apache.org/";
        try {
            jep.setPage(testUrlString);
        } catch (final IOException e) {
            assertTrue("Unexpected exception :" + e.getMessage(), false);
        }
        assertEquals(testUrlString, jep.getPage().toString());
    }
   //temporarily commented-out: HTMLEditorKit not implemented
   /* public void testGetStream() {
        try {
            Document doc = jep.getDocument();
            assertEquals(getClassName(TEST_URL.openStream()), getClassName(jep
                    .getStream(TEST_URL)));
            assertTrue(jep.getEditorKit() instanceof HTMLEditorKit);
            //4825653
            //System.out.println(jep.getDocument().getProperty(Document.StreamDescriptionProperty));
        } catch (final IOException e) {
            assertFalse("Unexpected exception: " + e.getMessage(), true);
        }
    }*/

    public void testReadHTML() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        jep.setContentType("text/html");
        byte bytes[] = htmlString.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        try {
            jep.read(stream, "text/html");
        } catch (IOException e) {
            assertFalse("Unexpected exception :", true);
        }
        assertEquals("text/html", jep.getContentType());
        /*
          * String docContent = null; try { Document doc = jep.getDocument();
          * docContent = doc.getText(0, doc.getLength()); }
          * catch(BadLocationException e){ assertFalse("Unexpected exception :",
          * true); } System.out.println("_____" + docContent + "_____"); String
          * temp = " \nThank you for the quote you sentreguarding account
          * #99999999999999. &160;I just have a couple of questions.Please let
          * me know. \n" + " Thank you, we look forward to hearing from you.";
          * System.out.println("___" + temp.replaceAll("\n","X") + "____");
          * System.out.println("___" + docContent.replaceAll("\n","X") +
          * "____"); System.out.println(temp.length() + " "+
          * docContent.length()); for (int i = 0 ; i < 182; i ++){
          * System.out.println("__" + temp.charAt(i) + "___"+
          * docContent.charAt(i)+ "___" + (temp.charAt(i) ==
          * docContent.charAt(i)) + " ..." + (docContent.charAt(i) == ' ')); }
          * assertTrue("BLA",temp.replaceAll("\n","X").equals(docContent.replaceAll("\n","X")));
          * //assertEquals(temp, docContent);
          * //assertEquals(htmlString,jep.getText().replaceAll("\n",""));
          *
          */

        assertEquals(removeMeta(htmlString), removeMeta(jep.getText()
                .replaceAll("\n", "").replaceAll("\r","")));
    }

    static final String removeMeta(final String s) {
        return s.replaceAll("meta[^>]*", "meta...");
    }

    public void testReadPlain() {
        byte bytes[] = plainString.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        try {
            jep.read(stream, "text/plain");
        } catch (IOException e) {
            assertFalse("Unexpected exception :", true);
        }
        assertEquals("text/plain", jep.getContentType());
        String docContent = null;
        assertEquals(plainString, jep.getText());
    }

    public void testReadRTF() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        jep.setContentType("text/rtf");
        byte bytes[] = rtfString.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        try {
            jep.read(stream, "text/rtf");
        } catch (IOException e) {
            assertFalse("Unexpected exception :", true);
        }
        assertEquals("text/rtf", jep.getContentType());
        String docContent = null;
        try {
            Document doc = jep.getDocument();
            docContent = doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            assertFalse("Unexpected exception :", true);
        }
        assertEquals("blablabla\n", docContent);
    }

    public void testScrollToReference() {

    }

    public void testSetGetContentType() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        assertEquals("text/plain", jep.getContentType());
        jep.setContentType("text/rtf");

        assertEquals("javax.swing.text.rtf.RTFEditorKit", jep.getEditorKit()
                .getClass().getName());
        assertEquals("javax.swing.text.DefaultStyledDocument", getClassName(jep
                .getDocument()));

        jep.setContentType("text/html");

        assertEquals("javax.swing.text.html.HTMLEditorKit", jep.getEditorKit()
                .getClass().getName());
        assertEquals("javax.swing.text.html.HTMLDocument", getClassName(jep
                .getDocument()));

        jep.setContentType("text/plain");
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", jep
                .getEditorKit().getClass().getName());
        assertEquals("javax.swing.text.PlainDocument", getClassName(jep
                .getDocument()));

        jep.setContentType("text/test");

        assertEquals("javax.swing.JEditorPane$PlainEditorKit", jep
                .getEditorKit().getClass().getName());
        assertEquals("javax.swing.text.PlainDocument", getClassName(jep
                .getDocument()));

        JEditorPane.
              registerEditorKitForContentType("text/test",
                              "javax.swing.text.DefaultEditorKit");
        jep.setContentType("text/test");
        assertEquals("javax.swing.text.DefaultEditorKit", jep.getEditorKit()
                .getClass().getName());
        assertEquals("javax.swing.text.PlainDocument", getClassName(jep
                .getDocument()));

        JEditorPane.registerEditorKitForContentType("text/test", "");
    }

    private String getClassName(final Object obj) {
        assertNotNull(obj);
        return obj.getClass().getName();
    }
    //commented due to unimplemented functionality
    /*public void testSetGetEditorKitForContentType() {
        String content = "testSetGetEditorKitForContentType";
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", getClassName(jep
                .getEditorKitForContentType("...")));
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", getClassName(jep
                .getEditorKitForContentType("text/plain")));
        assertEquals("javax.swing.text.html.HTMLEditorKit", getClassName(jep
                .getEditorKitForContentType("text/html")));
        assertEquals("javax.swing.text.rtf.RTFEditorKit", getClassName(jep
                .getEditorKitForContentType("text/rtf")));
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", getClassName(jep
                .getEditorKitForContentType(content)));

        JEditorPane
                .registerEditorKitForContentType(content,
                                                 "javax.swing.text.DefaultEditorKit");
        assertEquals("javax.swing.text.DefaultEditorKit", getClassName(jep
                .getEditorKitForContentType(content)));
        assertEquals("javax.swing.text.DefaultEditorKit", JEditorPane
                .getEditorKitClassNameForContentType(content));
        SimpleEditorKit kit = new SimpleEditorKit();
        jep.setEditorKitForContentType(content, kit);
        assertEquals("javax.swing.JEditorPaneTest$SimpleEditorKit",
                     getClassName(jep.getEditorKitForContentType(content)));
        assertEquals("javax.swing.text.DefaultEditorKit", JEditorPane
                .getEditorKitClassNameForContentType(content));
    }*/

    public void testSetGetEditorKit() {
        if (true) {
            throw new UnsupportedOperationException("Not implemented");
        }
        SimplePropertyChangeListener listener = new SimplePropertyChangeListener();
        jep.addPropertyChangeListener("editorKit", listener);
        PropertyChangeEvent event;
        EditorKit kit0 = jep.getEditorKit();
        SimpleEditorKit kit1 = new SimpleEditorKit();
        SimpleEditorKit kit2 = new SimpleEditorKit();
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", kit0.getClass()
                .getName());
        event = new PropertyChangeEvent(jep, "editorKit", kit0, kit1);
        jep.setEditorKit(kit1);
        assertEquals(kit1, jep.getEditorKit());
        assertEquals(event, listener.event);
        assertTrue(kit1.wasCallInstall);
        assertFalse(kit1.wasCallDeinstall);
        listener.resetDbgInfo();
        kit1.resetDbgInfo();

        event = new PropertyChangeEvent(jep, "editorKit", kit1, kit2);
        jep.setEditorKit(kit2);
        assertEquals(kit2, jep.getEditorKit());
        assertEquals(event, listener.event);

        assertTrue(kit2.wasCallInstall);
        assertFalse(kit2.wasCallDeinstall);

        assertFalse(kit1.wasCallInstall);
        assertTrue(kit1.wasCallDeinstall);

        kit2.resetDbgInfo();
        jep.setEditorKit(null);
        kit0 = jep.getEditorKit();
        event = new PropertyChangeEvent(jep, "editorKit", kit2, kit0);
        assertEquals("javax.swing.JEditorPane$PlainEditorKit", kit0.getClass()
                .getName());
        assertTrue(kit2.wasCallDeinstall);
        //temporarily commented-out: HTMLEditorKit,
        //DefaultStyledDocument not implemented
        /*assertEquals("text/plain", jep.getContentType());
        jep.setEditorKit(new HTMLEditorKit());
        assertEquals("text/html", jep.getContentType());
        assertEquals("javax.swing.text.html.HTMLDocument", getClassName(jep
                .getDocument()));

        jep.setEditorKit(new DefaultEditorKit());
        assertEquals("text/plain", jep.getContentType());
        assertEquals("javax.swing.text.PlainDocument", getClassName(jep
                .getDocument()));

        jep.setEditorKit(new StyledEditorKit());
        assertEquals("text/plain", jep.getContentType());
        assertEquals("javax.swing.text.DefaultStyledDocument", getClassName(jep
               .getDocument()));

        jep.setEditorKit(new RTFEditorKit());
        assertEquals("text/rtf", jep.getContentType());
        assertEquals("javax.swing.text.DefaultStyledDocument", getClassName(jep
                .getDocument())); */
    }

    public void testConstants() {
        assertEquals("JEditorPane.honorDisplayProperties",
                     JEditorPane.HONOR_DISPLAY_PROPERTIES);
        assertEquals("JEditorPane.w3cLengthUnits", JEditorPane.W3C_LENGTH_UNITS);
    }

    public void testPlainEditorKit() {
        EditorKit kit = jep.getEditorKit();
        assertEquals(kit, kit.getViewFactory());
    }
    public void testGetEditorKitClassNameForContentType(){
        assertEquals("javax.swing.JEditorPane$PlainEditorKit",
                     JEditorPane.getEditorKitClassNameForContentType("text/plain"));
        assertEquals("javax.swing.text.html.HTMLEditorKit",
                     JEditorPane.getEditorKitClassNameForContentType("text/html"));
        assertEquals("javax.swing.text.rtf.RTFEditorKit",
                     JEditorPane.getEditorKitClassNameForContentType("text/rtf"));
        assertNull(JEditorPane.getEditorKitClassNameForContentType("..."));
    }
}