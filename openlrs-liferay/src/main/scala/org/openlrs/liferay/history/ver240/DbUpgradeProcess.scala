package org.openlrs.liferay.history.ver240

import com.arcusys.valamis.lrs.liferay.UpgradeProcess
import org.openlrs.liferay.Lrs
import org.openlrs.liferay.history.SQLRunner

class DbUpgradeProcess extends UpgradeProcess with SQLRunner {
  override def getThreshold = 240
  val schema = new DbSchemaUpgrade(driver, db, Lrs)

  override def doUpgrade() {
    logger.info("Upgrading to 2.4")

    if(logger.isDebugEnabled)
      logger.debug(schema.upgradeMigrations.migrations.mkString(";\n"))

    if(schema.hasNotTables) {
      logger.info("Applying database schema changes")
      schema.upgrade
    } else {
      logger.info("Tables for version 2.4 exists already")
      
      val dataMigration = new DataUpgrade(Lrs)
      dataMigration.upgrade
    }
  }
}

