/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.axelor.demo;

import com.axelor.inject.Beans;
import com.axelor.meta.MetaFiles;
import com.axelor.meta.db.MetaFile;
import com.axelor.sale.db.Product;
import java.nio.file.Path;
import java.util.Map;

public class ProductImport {

  private static final String PRODUCT_IMAGES_DIR = "product_images";

  public Object importProduct(Object bean, Map context) {
    Product product = (Product) bean;

    final Path path = (Path) context.get("__path__");

    try {
      final Path image =
          ImportUtils.findByFileName(path.resolve(PRODUCT_IMAGES_DIR), product.getCode());
      if (image != null && image.toFile().exists()) {
        final MetaFile metaFile = Beans.get(MetaFiles.class).upload(image.toFile());
        product.setImage(metaFile);
      }
    } catch (Exception e) {
      // ignore
    }

    return product;
  }
}
