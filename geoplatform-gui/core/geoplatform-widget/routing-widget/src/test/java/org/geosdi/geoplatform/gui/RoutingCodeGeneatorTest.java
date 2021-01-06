/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

/**
 * @author giuseppe
 * 
 */
public class RoutingCodeGeneatorTest {

	private static final String SOURCE_FOLDER = "src/main/java/org/geosdi/geoplatform/gui/client/";

	private static final String TARGET_FOLDER = "target/client/";

	@Test
	public void createSampleSourceCode() {
		File folder = new File(SOURCE_FOLDER);
		File targetDir = new File(TARGET_FOLDER);
		if (!targetDir.exists())
			targetDir.mkdir();

		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				scanFolder(file);
			}
		}
	}

	private void scanFolder(File folder) {
		for (File file : folder.listFiles()) {
			if (!file.isDirectory() && file.getName().endsWith(".java")) {
				createFile(file, TARGET_FOLDER + folder.getName() + "/");
			}
		}
	}

	private void createFile(File file, String folder) {
		try {
			String javaContent = new String(read(new FileInputStream(file)));

			String fileName = file.getName().substring(0,
					file.getName().length() - 4)
					+ "txt";

			File publicFile = new File(folder + fileName);

			File folderFile = new File(folder);
			folderFile.mkdir();
			FileOutputStream out = new FileOutputStream(publicFile);
			out.write(javaContent.getBytes());
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(32768);
		byte[] buffer = new byte[1024];
		int count = in.read(buffer);
		while (count != -1) {
			if (count != 0) {
				out.write(buffer, 0, count);
			}
			count = in.read(buffer);
		}
		in.close();
		return out.toByteArray();
	}

}
