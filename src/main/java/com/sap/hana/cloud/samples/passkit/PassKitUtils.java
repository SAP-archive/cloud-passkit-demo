package com.sap.hana.cloud.samples.passkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import de.brendamour.jpasskit.PKPass;
import de.brendamour.jpasskit.signing.PKSigningInformation;
import de.brendamour.jpasskit.signing.PKSigningUtil;

/**
 * Simple class demonstrating how-to create and sign a PassKit pass.
 */
public class PassKitUtils
{

	/**
	 * Loads the JSON file with the specified path and returns it as {@link PKPass} object.
	 * 
	 * @return The {@link PKPass} object
	 */
	public PKPass loadPass(String jsonPath)
	{
		PKPass retVal = null;

		ObjectMapper jsonObjectMapper = new ObjectMapper();

		try
		{
			InputStream inStream = ClassLoader.getSystemResourceAsStream(jsonPath);
			retVal = jsonObjectMapper.readValue(inStream, PKPass.class);
		}
		catch (JsonParseException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (JsonMappingException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return retVal;
	}

	/**
	 * Signs and packages (zips) the content of the provided {@link PKPass} object.
	 * 
	 * @param pass The {@link PKPass} object to sign and package
	 * @param keyStorePassword The password used to sign the certificate (p12)
	 * @param templateFolder The name of the template folder (containing the images)
	 * @return The content of the signed and packaged {@link PKPass} object as a byte array.
	 */
	public byte[] signPass(PKPass pass, String keyStorePassword, String templateFolder)
	{

		byte[] retVal = null;

		final URL keyStoreURL = ClassLoader.getSystemResource("certs/Certificates.p12");
		final URL appleWWDRCAURL = ClassLoader.getSystemResource("certs/wwdr.pem");
		final URL templateFolderURL = ClassLoader.getSystemResource(templateFolder);

		PKSigningInformation pkSigningInformation = null;

		try
		{
			pkSigningInformation = PKSigningUtil.loadSigningInformationFromPKCS12FileAndIntermediateCertificateFile(keyStoreURL.getPath(),
			        keyStorePassword, appleWWDRCAURL.getPath());
		}
		catch (UnrecoverableKeyException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (NoSuchAlgorithmException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (CertificateException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (KeyStoreException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (NoSuchProviderException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		if (pkSigningInformation != null)
		{
			try
			{
				retVal = PKSigningUtil.createSignedAndZippedPkPassArchive(pass, templateFolderURL.getPath(), pkSigningInformation);
			}
			catch (Exception ex)
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		else
		{
			// TODO
		}

		return retVal;
	}

	/**
	 * Writes the content of the specified byte array into a file with the given filename.
	 * 
	 * @param content The content of the signed and packaged {@link PKPass} object as a byte array
	 * @param filename The name of the {@link File} to create
	 */
	public void createFile(byte[] content, String filename)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(content);
			fos.close();
		}
		catch (FileNotFoundException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
}
