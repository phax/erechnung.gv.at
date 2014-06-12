/**
 * Copyright (C) 2014 Philip Helger
 * ph[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.erechnung.erb.ws120;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import at.gv.brz.eproc.erb.ws.documentupload._20121205.AttachmentType;
import at.gv.brz.eproc.erb.ws.documentupload._20121205.SettingsType;
import at.gv.brz.schema.eproc.invoice_uploadstatus_1_0.TypeUploadStatus;

import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.xml.serialize.XMLReader;

/**
 * Unit test class for class {@link WS120Sender}.
 * 
 * @author Philip Helger
 */
public final class WS120SenderTest
{
  private static final String USP_WS_USERNAME = "xx";
  private static final String USP_WS_PASSWORD = "yy";

  /**
   * Basic test case. It is ignored by default, since no test username and
   * password are present. After setting {@link #USP_WS_USERNAME} and
   * {@link #USP_WS_PASSWORD} constants in this class, this test can be
   * "un-ignored".
   * 
   * @throws SAXException
   *         in case XML reading fails
   */
  @Test
  @Ignore
  public void testDeliverInvoice1 () throws SAXException
  {
    final Node aXMLDocument = XMLReader.readXMLDOM (new ClassPathResource ("test-invoices/ebi40.xml"));
    assertNotNull ("Failed to read example invoice", aXMLDocument);

    final WS120Sender aSender = new WS120Sender (USP_WS_USERNAME, USP_WS_PASSWORD);
    aSender.setDebugMode (true);
    aSender.setTestVersion (true);

    // No attachments
    final List <AttachmentType> aAttachments = null;

    final SettingsType aSettings = new SettingsType ();
    // Perform only technical validation
    aSettings.setTest (Boolean.TRUE);

    // Deliver it
    final TypeUploadStatus aResult = aSender.deliverInvoice (aXMLDocument, aAttachments, aSettings);
    assertNotNull (aResult.toString (), aResult.getSuccess ());
  }
}