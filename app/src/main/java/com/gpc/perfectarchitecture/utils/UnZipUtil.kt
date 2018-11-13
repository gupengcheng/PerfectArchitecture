package com.gpc.perfectarchitecture.utils

import java.io.File
import java.io.File.separator
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


/*
* @NAME: UnZipUtil
* @Package: com.gpc.perfectarchitecture.utils
* @PoemAuthor : pcg
* @Create at : 2018/11/13 上午10:32
* @Description: 
*/
class UnZipUtil {

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun unZipFile(zipFilePath: String, destDir:String):Boolean {
            val fileZip = zipFilePath
            val destDir = File(destDir)
            val buffer = ByteArray(1024)
            val zis = ZipInputStream(FileInputStream(fileZip))
            var zipEntry = zis.getNextEntry()
            while (zipEntry != null) {
                val newFile = newFile(destDir, zipEntry!!)
                val fos = FileOutputStream(newFile)
                var len: Int = zis.read(buffer)
                while (len >= 0) {
                    fos.write(buffer, 0, len)
                    len = zis.read(buffer)
                }
                fos.close()
                zipEntry = zis.getNextEntry()
            }
            zis.closeEntry()
            zis.close()
            return true
        }

        @Throws(IOException::class)
        private fun newFile(destinationDir: File, zipEntry: ZipEntry): File {
            val destFile = File(destinationDir, zipEntry.getName())

            val destDirPath = destinationDir.getCanonicalPath()
            val destFilePath = destFile.getCanonicalPath()

            if (!destFilePath.startsWith(destDirPath + File.separator)) {
                throw IOException("Entry is outside of the target dir: " + zipEntry.getName())
            }

            return destFile
        }
    }

}