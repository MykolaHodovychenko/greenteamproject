package com.ranpeak.ProjectX.dataBase.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ranpeak.ProjectX.dataBase.local.dao.NetworkDAO;
import com.ranpeak.ProjectX.dataBase.local.dao.ProfileDAO;
import com.ranpeak.ProjectX.dataBase.local.dao.ResumeDAO;
import com.ranpeak.ProjectX.dataBase.local.dao.TaskDAO;

import com.ranpeak.ProjectX.dataBase.local.dao.UserDAO;
import com.ranpeak.ProjectX.dto.MyResumeDTO;
import com.ranpeak.ProjectX.dto.MyTaskDTO;
import com.ranpeak.ProjectX.dto.ResumeDTO;
import com.ranpeak.ProjectX.dto.SocialNetworkDTO;
import com.ranpeak.ProjectX.dto.TaskDTO;

@Database(entities = {TaskDTO.class, MyTaskDTO.class, ResumeDTO.class, MyResumeDTO.class, SocialNetworkDTO.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {

    private static LocalDB instance;

    public abstract TaskDAO taskDao();

    public abstract ResumeDAO resumeDAO();

    public abstract NetworkDAO networkDAO();

    public abstract ProfileDAO profileDAO();

    public abstract UserDAO userDAO();

    public static synchronized LocalDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDB.class, "_task_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}