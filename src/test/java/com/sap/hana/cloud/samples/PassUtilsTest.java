package com.sap.hana.cloud.samples;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sap.hana.cloud.samples.passkit.PassKitUtils;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import de.brendamour.jpasskit.PKBarcode;
import de.brendamour.jpasskit.PKField;
import de.brendamour.jpasskit.PKPass;
import de.brendamour.jpasskit.enums.PKBarcodeFormat;
import de.brendamour.jpasskit.enums.PKTextAlignment;
import de.brendamour.jpasskit.passes.PKCoupon;

public class PassUtilsTest
{

	static Properties props = new Properties();
	
	@BeforeClass
	public static void loadProperties() throws IOException
	{
		InputStream inStream = ClassLoader.getSystemResourceAsStream("pass.properties");
	
		props.load(inStream);
	}
	
	@Test
	public void testPassCreation() throws Exception
	{
		PassKitUtils issuer = new PassKitUtils();
		
		PKPass pass = PassUtilsTest.createPass();
				
		byte[] content = issuer.signPass(pass, props.getProperty("cert.password"), "devedition-coupon");
		issuer.createFile(content, "hcp-dev-edition.pkpass");
	}
	
	
	/**
	 * Creates a new {@link PKPass} object using hard-coded content. (quick & dirty)
	 * 
	 * @return The {@link PKPass} object
	 */
	public static PKPass createPass()
	{
		PKPass retVal = new PKPass();

		// set basic info
		retVal.setFormatVersion(1);
		retVal.setPassTypeIdentifier(props.getProperty("pass.passTypeIdentifier"));
		retVal.setSerialNumber("001");
		retVal.setTeamIdentifier(props.getProperty("pass.teamIdentifier"));
		retVal.setOrganizationName(props.getProperty("pass.organisationName"));
		retVal.setLogoText("SAP HANA Cloud Platform");
		retVal.setDescription("SAP HANA Cloud Platform - Developer Edition");

		retVal.setBackgroundColor("rgb(255, 255, 255)");
		retVal.setForegroundColor("rgb(0, 0, 0)");
		retVal.setLabelColor("rgb(255, 255, 255)");

		PKCoupon coupon = new PKCoupon();
		
		// secondary fields
		List<PKField> secondaryFields = new ArrayList<PKField>();
		PKField secondaryField = new PKField();
		secondaryField.setKey("notricks");
		secondaryField.setLabel("// no tricks!");
		secondaryField.setValue("// free && perpetual");
		secondaryField.setTextAlignment(PKTextAlignment.PKTextAlignmentCenter);
		secondaryFields.add(secondaryField);
		coupon.setSecondaryFields(secondaryFields);
		
		// auxiliary fields
		List<PKField> auxiliaryFields = new ArrayList<PKField>();
		PKField auxField = new PKField();
		auxField.setKey("try");
		auxField.setLabel("Give it a try!");
		auxField.setValue("https://hanatrial.ondemand.com");
		auxField.setTextAlignment(PKTextAlignment.PKTextAlignmentCenter);
		auxiliaryFields.add(auxField);
		coupon.setAuxiliaryFields(auxiliaryFields);
		
		// back fields
		List<PKField> backFields = new ArrayList<PKField>();
		
		PKField backField1 = new PKField();
		backField1.setKey("url");
		backField1.setLabel("URL:");
		backField1.setValue("https://hanatrial.ondemand.com");
		
		PKField backField2 = new PKField();
		backField2.setKey("twitter");
		backField2.setLabel("Twitter:");
		backField2.setValue("http://twitter.com/saphcp");
		
		backFields.add(backField1);
		backFields.add(backField2);
		coupon.setBackFields(backFields);

		// barcode
		PKBarcode barcode = new PKBarcode();
		barcode.setFormat(PKBarcodeFormat.PKBarcodeFormatQR);
		barcode.setMessage("https://hanatrial.ondemand.com");
		barcode.setMessageEncoding(Charset.forName("iso-8859-1"));
		retVal.setBarcode(barcode);

		retVal.setCoupon(coupon);

		return retVal;
	}
}


