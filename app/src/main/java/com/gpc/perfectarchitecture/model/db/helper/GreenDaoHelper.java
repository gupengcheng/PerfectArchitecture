package com.gpc.perfectarchitecture.model.db.helper;

import android.os.Environment;
import com.gpc.perfectarchitecture.model.db.entity.*;
import com.gpc.perfectarchitecture.utils.Constant;

import java.io.File;
import java.util.List;

/*
 * @NAME: GreenDaoHelper
 * @Package: com.gpc.perfectarchitecture.model.db.helper
 * @PoemAuthor : pcg
 * @Create at : 2018/11/13 下午2:21
 * @Description:
 */
public class GreenDaoHelper {

    private DaoSession mPoemDS; // 诗词
    private DaoSession mAuthorDS; // 作者
    private DaoSession mLunYuDS; // 论语

    /**
     * DAO具体负责数据库操作
     */
    private PoemsDao mPoemsDao;
    private PoemAuthorDao mAuthorDao;
    private LunyuDao mLunYuDao;

    private static GreenDaoHelper mInstance;

    private GreenDaoHelper() {
        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + Constant.SDCARD_FILE_DIRECTORY_NAME;
        File sdCardDirectoryFile = new File(fileDir);
        if (sdCardDirectoryFile.exists()) {
            String poemDbFilePath = fileDir + File.separator + Constant.POEM_FILE_NAME;
            String authorDbFilePath = fileDir + File.separator + Constant.POEM_AUTHOR_FILE_NAME;
            String lunYuDbFilePath = fileDir + File.separator + Constant.LUNYU_FILE_NAME;

            DaoMaster daoMasterPoem = new DaoMaster(SQLHelper.openDatabase(poemDbFilePath));
            mPoemDS = daoMasterPoem.newSession();
            mPoemsDao = mPoemDS.getPoemsDao();

            DaoMaster daoMasterAuthor = new DaoMaster(SQLHelper.openDatabase(authorDbFilePath));
            mAuthorDS = daoMasterAuthor.newSession();
            mAuthorDao = mAuthorDS.getPoemAuthorDao();

            DaoMaster daoMasterLunYu = new DaoMaster(SQLHelper.openDatabase(lunYuDbFilePath));
            mLunYuDS = daoMasterLunYu.newSession();
            mLunYuDao = mLunYuDS.getLunyuDao();
        }
    }

    public static GreenDaoHelper getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoHelper.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 分页查询Author
     *
     * @param page     从1开始
     * @param pageSize >0
     * @return
     */
    public List<PoemAuthor> queryAuthor(int page, int pageSize) {
        List<PoemAuthor> poemAuthorList = mAuthorDao.queryBuilder().limit(pageSize).offset((page - 1) * pageSize).list();
        return poemAuthorList;
    }

    /**
     * 分页查询Poem
     *
     * @param page     从1开始
     * @param pageSize >0
     * @return
     */
    public List<Poems> queryPoem(int page, int pageSize) {
        List<Poems> poemsList = mPoemsDao.queryBuilder().limit(pageSize).offset((page - 1) * pageSize).list();
        return poemsList;
    }

    /**
     * 分页查询Lunyu
     *
     * @param page     从1开始
     * @param pageSize >0
     * @return
     */
    public List<Lunyu> queryLunYu(int page, int pageSize) {
        List<Lunyu> lunYuList = mLunYuDao.queryBuilder().limit(pageSize).offset((page - 1) * pageSize).list();
        return lunYuList;
    }

}
