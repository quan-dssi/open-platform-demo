/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2021 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.data;

import com.axelor.data.csv.CSVImporter;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

public class CSVDataTest extends AbstractTest {

  @Test
  public void testDefault() throws IOException {
    Importer importer = new CSVImporter("data/csv-config.xml", "data/csv");
    importer.run();
  }

  @Test
  public void testMulti() throws IOException {
    Importer importer = new CSVImporter("data/csv-multi-config.xml");
    importer.run(
        new ImportTask() {
          @Override
          public void configure() throws IOException {
            input("[sale.order]", new File("data/csv-multi/so1.csv"));
            input("[sale.order]", new File("data/csv-multi/so2.csv"));
          }
        });
  }

  @Test
  public void testData() throws IOException {
    Importer importer = new CSVImporter("data/csv-config-types.xml", "data/csv");
    importer.run();
  }
}
