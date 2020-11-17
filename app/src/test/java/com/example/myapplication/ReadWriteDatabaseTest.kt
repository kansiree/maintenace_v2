package com.example.myapplication

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myapplication.Database.AppDatabase
import com.example.myapplication.Database.Dao.TechnicalOrderDao
import com.example.myapplication.Database.Entity.TechnicalOrderEntity
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class ReadWriteDatabaseTest {
    private lateinit var techDao: TechnicalOrderDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        techDao = db.technicalOrderDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadInList() {
        val techEntity: TechnicalOrderEntity = TechnicalOrderEntity(1, "createDate", "fullName")
        techDao.insertTechnicalOrder(techEntity)
        val techItem = techDao.findByFullName(techEntity.fullName)
        Assert.assertThat(techItem, IsEqual.equalTo(techEntity))
    }

    @Test
    @Throws(Exception::class)
    fun delete() {
        val techEntity: TechnicalOrderEntity = TechnicalOrderEntity(1, "createDate", "fullName")
        techDao.deleteTechnicalOrder(techEntity)
        Assert.assertEquals(techEntity, null)
    }
}