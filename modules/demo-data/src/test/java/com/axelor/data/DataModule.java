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

import com.axelor.app.AppModule;
import com.axelor.auth.AuthModule;
import com.axelor.db.JpaModule;
import com.axelor.rpc.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import net.sf.ehcache.CacheManager;

public class DataModule extends AbstractModule {

  @Override
  protected void configure() {

    // shutdown the cache manager if running
    if (CacheManager.ALL_CACHE_MANAGERS.size() > 0) {
      CacheManager.getInstance().shutdown();
    }

    bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);

    install(new JpaModule("testUnit", true, true));
    install(new AuthModule());
    install(new AppModule());

    configureImport();
  }

  protected void configureImport() {}
}
