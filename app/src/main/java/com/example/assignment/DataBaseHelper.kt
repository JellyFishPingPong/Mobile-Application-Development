package com.example.assignment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CONTEXT = context

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "farmanac.db"


        // Table Names
        private const val TBL_MEMBER = "tbl_member"
        private const val TBL_FORUM = "tbl_forum"
        private const val TBL_FORUM_LIKE = "tbl_forum_like"
        private const val TBL_FORUM_COMMENT = "tbl_forum_comment"
        private const val TBL_REPORTED_FORUM = "tbl_reported_forum"
        private const val TBL_COMMENT_LIKE = "tbl_comment_like"
        private const val TBL_BOOK_RECORD = "tbl_book_record"
        private const val TBL_GUIDE = "tbl_guide"
        private const val TBL_CONSULTANT = "tbl_consultant"
        private const val TBL_BORROW = "tbl_borrow "

        // Member table columns
        private const val ID = "mem_id"
        private const val PROFILE_PIC = "profile_pic"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val PW = "pw"
        private const val CONTACT_NUM = "contact_num"
        private const val OCCUPATION = "occupation"
        private const val COUNTRY = "country"


        // Forum table columns
        //uses ID
        private const val FOR_ID = "forum_id"
        private const val TITLE = "forum_title"
        private const val DESC = "forum_description"
        private const val UP_DATE = "forum_upload_date"
        private const val TIMEZONE = "timezone"

        // Forum likes table columns
        //uses FOR_ID and ID

        // Forum comment table columns
        //uses FOR_ID and ID
        private const val COM_ID = "comment_id"
        private const val PAR_ID = "parent_id"
        private const val COMMENT = "comment"

        // Reported forum table columns
        //uses FOR_ID and ID
        private const val REASON = "report_reason"

        // Comment like table columns
        //uses COM_ID and ID

        // BooK Record table columns
        //uses ID
        private const val BOOKING_ID = "booking_id"
        private const val DATE_SELECTED = "date_selected"
        private const val TIME_SELECTED = "time_selected"

        // CONSULTANT TABLE
        private const val CONSULTANT_ID = "consultant_id"
        private const val CONSULTANT_NAME = "consultant_name"

        // GUIDE table columns
        private const val GUIDE_ID = "guide_id"
        private const val GUIDE_TITLE = "guide_title"
        private const val GUIDE_IMAGE = "guide_image"
        private const val GUIDE_METHOD = "guide_method"
        private const val GUIDE_CONTENT = "guide_content"

        // Booking Table columns
        private const val WEBSITE_NAME = "website_name"
        private const val BORROW_ID = "borrow_id"
        private const val URL_DES = "url_des"

        // Functions
        fun getNextId(context: Context, tableName: String, column: String): Int {
            val selectQuery = "SELECT $column FROM $tableName ORDER BY $column DESC LIMIT 1"
            val db = DataBaseHelper(context).readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            return if (cursor.moveToFirst()) {
                cursor.getInt(0) + 1
            } else {
                1
            }
        }

        fun insertRecord(context: Context, tableName: String, values: ContentValues): Long {
            val db = DataBaseHelper(context).writableDatabase
            //            db.close()
            return db.insert(tableName, null, values)
        }

        // Member Table
        class MemberTable(context: Context) {
            private var mContext: Context = context

            // Creating Member table
            fun createTable(db: SQLiteDatabase) {
                val createMemberTable =
                    ("CREATE TABLE $TBL_MEMBER ($ID INTEGER PRIMARY KEY, $PROFILE_PIC INTEGER, $USERNAME TEXT, $EMAIL TEXT, $PW TEXT, " +
                            "$CONTACT_NUM TEXT, $OCCUPATION TEXT, $COUNTRY TEXT)")
                db.execSQL(createMemberTable)
            }

            fun insertMember(user: UserModel): Long {
                val contentValues = ContentValues()
                contentValues.put(ID, getNextId(mContext, TBL_MEMBER, ID))
                contentValues.put(PROFILE_PIC, user.profilePic)
                contentValues.put(USERNAME, user.username)
                contentValues.put(EMAIL, user.email)
                contentValues.put(PW, user.pw)
                contentValues.put(CONTACT_NUM, user.contactNum)
                contentValues.put(OCCUPATION, user.occupation)
                contentValues.put(COUNTRY, user.country)

                return insertRecord(mContext, TBL_MEMBER, contentValues)
            }

            fun validLogin(username: String, password: String): Int {
                val db = DataBaseHelper(mContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_MEMBER WHERE $USERNAME = '$username' AND $PW = '$password'"
                val id: Int

                val cursor = db.rawQuery(selectQuery, null)
                id = if (cursor.moveToFirst()) {
                    cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                } else {
                    -1
                }
                cursor.close()
                return id
            }

            //check if current username exists in db
            fun usernameExist(username: String): Int {
                val db = DataBaseHelper(mContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_MEMBER WHERE $USERNAME = '$username'"
                val id: Int

                val cursor = db.rawQuery(selectQuery, null)
                id = if (cursor.moveToFirst()) {
                    cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                } else {
                    -1
                }
                cursor.close()
                return id
            }

            //check if email exist
            fun emailExist(email: String): Int {
                val db = DataBaseHelper(mContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_MEMBER WHERE $EMAIL = '$email'"
                val id: Int

                val cursor = db.rawQuery(selectQuery, null)
                id = if (cursor.moveToFirst()) {
                    cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                } else {
                    -1
                }
                cursor.close()
                return id
            }

            fun deleteMember(memID: Int) {
                val db = DataBaseHelper(mContext)
                val writer = db.writableDatabase

                writer.delete(TBL_BOOK_RECORD, "$ID=?", arrayOf(memID.toString()))
                writer.delete(TBL_REPORTED_FORUM, "$ID=?", arrayOf(memID.toString()))
                writer.delete(TBL_FORUM_LIKE, "$ID=?", arrayOf(memID.toString()))
                writer.delete(TBL_COMMENT_LIKE, "$ID=?", arrayOf(memID.toString()))
                writer.delete(TBL_FORUM_COMMENT, "$ID=?", arrayOf(memID.toString()))
                writer.delete(TBL_FORUM, "$ID=?", arrayOf(memID.toString()))
                writer.delete(TBL_MEMBER, "$ID=?", arrayOf(memID.toString()))
                Log.d("DBHELPER", "removed member $memID")
            }

            fun getAllMember(): ArrayList<UserModel> {
                val userList: ArrayList<UserModel> = ArrayList()
                val selectQuery = "SELECT * FROM $TBL_MEMBER"
                val db = DataBaseHelper(mContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var profilePic: Int
                var username: String
                var email: String
                var pw: String
                var contactNum: String
                var occupation: String
                var country: String

                //loop through all rows & add to list
                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                        profilePic = cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_PIC))
                        username = cursor.getString(cursor.getColumnIndexOrThrow(USERNAME))
                        email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL))
                        pw = cursor.getString(cursor.getColumnIndexOrThrow(PW))
                        contactNum = cursor.getString(cursor.getColumnIndexOrThrow(CONTACT_NUM))
                        occupation = cursor.getString(cursor.getColumnIndexOrThrow(OCCUPATION))
                        country = cursor.getString(cursor.getColumnIndexOrThrow(COUNTRY))

                        val user = UserModel(
                            id,
                            profilePic,
                            username,
                            email,
                            pw,
                            contactNum,
                            occupation,
                            country
                        )

                        userList.add(user)
                    } while (cursor.moveToNext())
                }

                return userList
            }

            fun getMember(mem_id: Int): UserModel {
                val db = DataBaseHelper(mContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_MEMBER WHERE $ID = $mem_id"

                val cursor: Cursor

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return UserModel()
                }

                //loop through all rows & add to list
                if (cursor.moveToFirst()) {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val profilePic: Int =
                        cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_PIC))
                    val username: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(USERNAME))
                    val email: String = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL))
                    val pw: String = cursor.getString(cursor.getColumnIndexOrThrow(PW))
                    val contactNum: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(CONTACT_NUM))
                    val occupation: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(OCCUPATION))
                    val country: String = cursor.getString(cursor.getColumnIndexOrThrow(COUNTRY))

                    return UserModel(
                        id,
                        profilePic,
                        username,
                        email,
                        pw,
                        contactNum,
                        occupation,
                        country
                    )
                }

                return UserModel()
            }

            //Update member data
            fun updateMember(user: UserModel): Int {
                val db = DataBaseHelper(mContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(PROFILE_PIC, user.profilePic)
                contentValues.put(USERNAME, user.username)
                contentValues.put(EMAIL, user.email)
                contentValues.put(CONTACT_NUM, user.contactNum)
                contentValues.put(OCCUPATION, user.occupation)
                contentValues.put(COUNTRY, user.country)

                val success = db.update(TBL_MEMBER, contentValues, "mem_id= ${user.id}", null)
                db.close()
                return success
            }

            fun changePw(mem_id: Int, user: UserModel): Int {
                val db = DataBaseHelper(mContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(PW, user.pw)

                val success = db.update(TBL_MEMBER, contentValues, "mem_id= $mem_id", null)
                db.close()
                return success
            }


            fun getMemberViaUsername(username: String): UserModel {
                val db = DataBaseHelper(mContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_MEMBER WHERE $USERNAME = '$username'"

                val cursor: Cursor

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return UserModel()
                }

                //loop through all rows & add to list
                if (cursor.moveToFirst()) {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val profilePic: Int =
                        cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_PIC))
                    val name: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(USERNAME))
                    val email: String = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL))
                    val pw: String = cursor.getString(cursor.getColumnIndexOrThrow(PW))
                    val contactNum: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(CONTACT_NUM))
                    val occupation: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(OCCUPATION))
                    val country: String = cursor.getString(cursor.getColumnIndexOrThrow(COUNTRY))

                    return UserModel(
                        id,
                        profilePic,
                        name,
                        email,
                        pw,
                        contactNum,
                        occupation,
                        country
                    )
                }
                return UserModel()
            }
        }

        // Forum Table
        class ForumTable(context: Context) {
            private var fContext: Context = context

            // Creating Forum table
            fun createTable(db: SQLiteDatabase) {
                val createForumTable =
                    ("CREATE TABLE $TBL_FORUM ($FOR_ID INTEGER PRIMARY KEY, $TITLE TEXT, $DESC TEXT, $UP_DATE TEXT," +
                            "$TIMEZONE TEXT, $ID INTEGER, FOREIGN KEY($ID) REFERENCES $TBL_MEMBER($ID))")
                db.execSQL(createForumTable)
            }

            fun insertForum(forum: Forum): Long {
                val db = DataBaseHelper(fContext).writableDatabase

                val contentValues = ContentValues()
                contentValues.put(FOR_ID, getNextId(fContext, TBL_FORUM, FOR_ID))
                contentValues.put(TITLE, forum.title)
                contentValues.put(DESC, forum.description)
                contentValues.put(UP_DATE, forum.uploadDate)
                contentValues.put(ID, forum.mem_id)
                contentValues.put(TIMEZONE, forum.timeZone)

                val success = db.insert(TBL_FORUM, null, contentValues)
                return success
            }

            fun getForum(for_id: Int): Forum {
                val db = DataBaseHelper(fContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_FORUM WHERE $FOR_ID = $for_id"

                val cursor: Cursor

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return Forum()
                }

                //loop through all rows & add to list
                if (cursor.moveToFirst()) {
                    val id: Int = cursor.getInt(cursor.getColumnIndexOrThrow(FOR_ID))
                    val memId: Int =
                        cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val title: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
                    val desc: String = cursor.getString(cursor.getColumnIndexOrThrow(DESC))
                    val uploadDate: String = cursor.getString(cursor.getColumnIndexOrThrow(UP_DATE))
                    val timeZone: String =
                        cursor.getString(cursor.getColumnIndexOrThrow(TIMEZONE))

                    return Forum(id, memId, title, desc, uploadDate, timeZone)
                }
                return Forum()
            }

            fun deleteForum(forID: Int) {
                val db = DataBaseHelper(fContext)
                val writer = db.writableDatabase
                val forum = getForum(forID)

                writer.delete(TBL_REPORTED_FORUM, "$FOR_ID=?", arrayOf(forID.toString()))
                writer.delete(TBL_COMMENT_LIKE, "$ID=?", arrayOf(forum.mem_id.toString()))
                writer.delete(TBL_FORUM_LIKE, "$FOR_ID=?", arrayOf(forID.toString()))
                writer.delete(TBL_FORUM_COMMENT, "$FOR_ID=?", arrayOf(forID.toString()))
                writer.delete(TBL_FORUM, "$FOR_ID=?", arrayOf(forID.toString()))
                Log.d("DBHELPER", "removed forum $forID")
            }

            fun editForum(forID: Int, title: String, desc: String) {
                val db = DataBaseHelper(fContext).writableDatabase
                val values = ContentValues().apply {
                    put(TITLE, title)
                    put(DESC, desc)
                }
                val selection = "$FOR_ID = ?"
                val selectionArgs = arrayOf(forID.toString())
                db.update(TBL_FORUM, values, selection, selectionArgs)
            }

            fun getAllForum(): ArrayList<Forum> {
                val forList: ArrayList<Forum> = ArrayList()
                val selectQuery = "SELECT * FROM $TBL_FORUM ORDER BY $UP_DATE DESC"
                val db = DataBaseHelper(fContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var title: String
                var desc: String
                var upDate: String
                var timeZone: String
                var memId: Int

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(FOR_ID))
                        title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
                        desc = cursor.getString(cursor.getColumnIndexOrThrow(DESC))
                        upDate =
                            cursor.getString(cursor.getColumnIndexOrThrow(UP_DATE))
                        timeZone =
                            cursor.getString(cursor.getColumnIndexOrThrow(TIMEZONE))
                        memId = cursor.getInt(cursor.getColumnIndexOrThrow(ID))

                        val forum = Forum(id, memId, title, desc, upDate, timeZone)
                        forList.add(forum)
                    } while (cursor.moveToNext())
                }
                return forList
            }

            fun getAllLikedForum(memID: Int): ArrayList<Forum> {
                val forList: ArrayList<Forum> = ArrayList()
                val forIdList: ArrayList<Int> = ArrayList()
                val selectQuery = "SELECT $FOR_ID FROM $TBL_FORUM " +
                        "INTERSECT SELECT $FOR_ID FROM $TBL_FORUM_LIKE WHERE $ID = $memID"
                val db = DataBaseHelper(fContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(FOR_ID))

                        forIdList.add(id)
                    } while (cursor.moveToNext())
                }

                var reversed = forIdList.reversed()
                for (x in reversed) {
                    val forum = getForum(x)

                    forList.add(forum)
                }

                return forList
            }

            fun getAllPostedForum(memID: Int): ArrayList<Forum> {
                val forList: ArrayList<Forum> = ArrayList()
                val selectQuery = "SELECT * FROM $TBL_FORUM WHERE $ID = $memID ORDER BY $UP_DATE DESC"
                val db = DataBaseHelper(fContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var title: String
                var desc: String
                var upDate: String
                var timeZone: String
                var memId: Int

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(FOR_ID))
                        title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
                        desc = cursor.getString(cursor.getColumnIndexOrThrow(DESC))
                        upDate =
                            cursor.getString(cursor.getColumnIndexOrThrow(UP_DATE))
                        timeZone =
                            cursor.getString(cursor.getColumnIndexOrThrow(TIMEZONE))
                        memId = cursor.getInt(cursor.getColumnIndexOrThrow(ID))

                        val forum = Forum(id, memId, title, desc, upDate, timeZone)
                        forList.add(forum)
                    } while (cursor.moveToNext())
                }
                return forList
            }
        }

        // Forum Like Table
        class ForumLikeTable(context: Context) {
            private var fContext: Context = context

            // Creating Forum Like table
            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_FORUM_LIKE ($FOR_ID INTEGER, $ID INTEGER, FOREIGN KEY($FOR_ID) REFERENCES $TBL_FORUM($FOR_ID)," +
                            "FOREIGN KEY($ID) REFERENCES $TBL_MEMBER($ID), PRIMARY KEY($FOR_ID, $ID))")
                db.execSQL(createQuery)
            }

            fun getLikeAmount(forID: Int): Int {
                val selectQuery =
                    "SELECT * FROM $TBL_FORUM_LIKE WHERE $FOR_ID = $forID"
                val db = DataBaseHelper(fContext).readableDatabase
                var likeAmount = 0

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return 0
                }

                if (cursor.moveToFirst()) {
                    do {
                        likeAmount++
                    } while (cursor.moveToNext())
                }
                return likeAmount
            }

            fun liked(forID: Int, memID: Int): Boolean {
                val db = DataBaseHelper(fContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_FORUM_LIKE WHERE $FOR_ID = $forID AND $ID = $memID"
                val liked: Boolean

                val cursor = db.rawQuery(selectQuery, null)
                liked = cursor.moveToFirst()
                cursor.close()
                return liked
            }

            fun toggleLike(forID: Int, memID: Int) {

                val db = DataBaseHelper(fContext)
                if (liked(forID, memID)) {
                    val writer = db.writableDatabase

                    writer.delete(
                        TBL_FORUM_LIKE,
                        "$FOR_ID=? AND $ID=?",
                        arrayOf(forID.toString(), memID.toString())
                    )
                    Log.d("DBHELPER", "removed like from $forID by $memID")
                } else {
                    val read = db.readableDatabase

                    val contentValues = ContentValues()
                    contentValues.put(FOR_ID, forID)
                    contentValues.put(ID, memID)

                    read.insert(TBL_FORUM_LIKE, null, contentValues)
                    Log.d("DBHELPER", "added like to $forID by $memID")
//                    db.close()
                }
            }
        }

        // Forum Comment Table
        class ForumCommentTable(context: Context) {
            private var fContext: Context = context

            // Creating Forum Comment table
            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_FORUM_COMMENT ($COM_ID INTEGER PRIMARY KEY, $PAR_ID INTEGER, $COMMENT TEXT," +
                            " $FOR_ID INTEGER, $ID INTEGER, FOREIGN KEY($FOR_ID) REFERENCES $TBL_FORUM($FOR_ID)," +
                            "FOREIGN KEY($ID) REFERENCES $TBL_MEMBER($ID))")
                db.execSQL(createQuery)
            }

            fun insertComment(comment: Comment): Long {
                val db = DataBaseHelper(fContext).writableDatabase

                val contentValues = ContentValues()
                contentValues.put(COM_ID, getNextId(fContext, TBL_FORUM_COMMENT, COM_ID))
                contentValues.put(ID, comment.memId)
                contentValues.put(FOR_ID, comment.forumId)
                contentValues.put(PAR_ID, comment.parentId)
                contentValues.put(COMMENT, comment.comment)

                return db.insert(TBL_FORUM_COMMENT, null, contentValues)
            }

            fun getAllChild(parID: Int): ArrayList<Comment> {
                val comList: ArrayList<Comment> = ArrayList()
                val selectQuery = "SELECT * FROM $TBL_FORUM_COMMENT WHERE $PAR_ID = $parID"
                val db = DataBaseHelper(fContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var comment: String
                var forId: Int
                var memId: Int

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(COM_ID))
                        memId = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                        forId = cursor.getInt(cursor.getColumnIndexOrThrow(FOR_ID))
                        comment = cursor.getString(cursor.getColumnIndexOrThrow(COMMENT))

                        val commentRow = Comment(id, memId, forId, parID, comment)
                        comList.add(commentRow)
                    } while (cursor.moveToNext())
                }
                return comList
            }

            fun getAllComment(forID: Int): ArrayList<Comment> {
                val comList: ArrayList<Comment> = ArrayList()
                val selectQuery =
                    "SELECT * FROM $TBL_FORUM_COMMENT WHERE $FOR_ID = $forID"
                val db = DataBaseHelper(fContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var content: String
                var memId: Int
                var parId: Int

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(COM_ID))
                        memId = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                        parId = cursor.getInt(cursor.getColumnIndexOrThrow(PAR_ID))
                        content = cursor.getString(cursor.getColumnIndexOrThrow(COMMENT))

                        val comment = Comment(id, memId, forID, parId, content)
                        comList.add(comment)
                    } while (cursor.moveToNext())
                }
                return comList
            }


            fun getAllParent(forID: Int): ArrayList<Comment> {
                val comList: ArrayList<Comment> = ArrayList()
                val selectQuery =
                    "SELECT * FROM $TBL_FORUM_COMMENT WHERE $FOR_ID = $forID AND $PAR_ID = 0"
                val db = DataBaseHelper(fContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var content: String
                var memId: Int
                var parId: Int

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(COM_ID))
                        memId = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                        parId = cursor.getInt(cursor.getColumnIndexOrThrow(PAR_ID))
                        content = cursor.getString(cursor.getColumnIndexOrThrow(COMMENT))

                        val comment = Comment(id, memId, forID, parId, content)
                        comList.add(comment)
                    } while (cursor.moveToNext())
                }
                return comList
            }

        }

        class ForumReportTable(context: Context) {
            private var fContext = context

            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_REPORTED_FORUM ($FOR_ID INTEGER, $ID INTEGER, $REASON TEXT, FOREIGN KEY($FOR_ID) REFERENCES $TBL_FORUM($FOR_ID)," +
                            "FOREIGN KEY($ID) REFERENCES $TBL_MEMBER($ID), PRIMARY KEY($FOR_ID, $ID))")
                db.execSQL(createQuery)
            }

            fun reportForum(report: ForumReport): Long {
                val db = DataBaseHelper(fContext).writableDatabase
                val contentValues = ContentValues()

                contentValues.put(FOR_ID, report.forum_id)
                contentValues.put(ID, report.mem_id)
                contentValues.put(REASON, report.reason)

                return db.insert(TBL_REPORTED_FORUM, null, contentValues)
            }

            fun isReported(forID: Int, memID: Int): Boolean {
                val db = DataBaseHelper(fContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_REPORTED_FORUM WHERE $FOR_ID = $forID AND $ID = $memID"
                val reported: Boolean

                val cursor = db.rawQuery(selectQuery, null)
                reported = cursor.moveToFirst()
                cursor.close()
                return reported
            }
        }

        class CommentLikeTable(context: Context) {
            private val fContext = context

            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_COMMENT_LIKE ($COM_ID INTEGER, $ID INTEGER, FOREIGN KEY($COM_ID) REFERENCES $TBL_FORUM_COMMENT($COM_ID)," +
                            "FOREIGN KEY($ID) REFERENCES $TBL_MEMBER($ID), PRIMARY KEY($COM_ID, $ID))")
                db.execSQL(createQuery)
            }

            fun getLikeAmount(comID: Int): Int {
                val selectQuery =
                    "SELECT * FROM $TBL_COMMENT_LIKE WHERE $COM_ID = $comID"
                val db = DataBaseHelper(fContext).readableDatabase
                var likeAmount = 0

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return 0
                }

                if (cursor.moveToFirst()) {
                    do {
                        likeAmount++
                    } while (cursor.moveToNext())
                }
                return likeAmount
            }

            fun liked(comID: Int, memID: Int): Boolean {
                val db = DataBaseHelper(fContext).readableDatabase
                val selectQuery =
                    "SELECT * FROM $TBL_COMMENT_LIKE WHERE $COM_ID = $comID AND $ID = $memID"
                val liked: Boolean

                val cursor = db.rawQuery(selectQuery, null)
                liked = cursor.moveToFirst()
                cursor.close()
                return liked
            }

            fun toggleLike(comID: Int, memID: Int) {

                val db = DataBaseHelper(fContext)
                if (liked(comID, memID)) {
                    val writer = db.writableDatabase

                    writer.delete(
                        TBL_COMMENT_LIKE,
                        "$COM_ID=? AND $ID=?",
                        arrayOf(comID.toString(), memID.toString())
                    )
                    Log.d("DBHELPER", "removed like from $comID by $memID")
                } else {
                    val read = db.readableDatabase

                    val contentValues = ContentValues()
                    contentValues.put(COM_ID, comID)
                    contentValues.put(ID, memID)

                    read.insert(TBL_COMMENT_LIKE, null, contentValues)
                    Log.d("DBHELPER", "added like to $comID by $memID")
//                    db.close()
                }
            }
        }

        // Book Record Table
        class BookRecordTable(context: Context) {
            private val bookContext: Context = context
            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_BOOK_RECORD ($BOOKING_ID INTEGER PRIMARY KEY," +
                            "$CONSULTANT_ID INTEGER,$ID INTEGER, $TIME_SELECTED TEXT," +
                            " $DATE_SELECTED TEXT,FOREIGN KEY($CONSULTANT_ID) REFERENCES $TBL_CONSULTANT($CONSULTANT_ID)," +
                            "FOREIGN KEY($ID) REFERENCES $TBL_MEMBER($ID))")

                db.execSQL(createQuery)
            }

            fun getAllBooking(memID: Int): ArrayList<BookingModel> {
                val bookList: ArrayList<BookingModel> = ArrayList()
                val selectQuery =
                    "SELECT * FROM $TBL_BOOK_RECORD WHERE $ID = $memID ORDER BY $DATE_SELECTED"
                val db = DataBaseHelper(bookContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var conId: Int
                var time: String
                var date: String

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(BOOKING_ID))
                        conId = cursor.getInt(cursor.getColumnIndexOrThrow(CONSULTANT_ID))
                        time = cursor.getString(cursor.getColumnIndexOrThrow(TIME_SELECTED))
                        date = cursor.getString(cursor.getColumnIndexOrThrow(DATE_SELECTED))

                        val book = BookingModel(id, memID, conId, time, date)
                        bookList.add(book)
                    } while (cursor.moveToNext())
                }
                return bookList
            }

            fun insertBooking(book: BookingModel): Long {
                val db = DataBaseHelper(bookContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(BOOKING_ID, getNextId(bookContext, TBL_BOOK_RECORD, BOOKING_ID))
                contentValues.put(CONSULTANT_ID, book.consultantId)
                contentValues.put(ID, book.memId)
                contentValues.put(TIME_SELECTED, book.timeSelected)
                contentValues.put(DATE_SELECTED, book.dateSelected)
                return db.insert(Companion.TBL_BOOK_RECORD, null, contentValues)
            }

            fun getBook(bookId: Int): BookingModel {
                val selectQuery =
                    "SELECT * FROM $TBL_BOOK_RECORD WHERE $BOOKING_ID = $bookId"
                val db = DataBaseHelper(bookContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return BookingModel()
                }

                if (cursor.moveToFirst()) {
                    val memID = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val conId = cursor.getInt(cursor.getColumnIndexOrThrow(CONSULTANT_ID))
                    val time = cursor.getString(cursor.getColumnIndexOrThrow(TIME_SELECTED))
                    val date = cursor.getString(cursor.getColumnIndexOrThrow(DATE_SELECTED))

                    return BookingModel(bookId, memID, conId, time, date)
                }
                return BookingModel()
            }

            fun deleteBook(bookId: Int) {
                val db = DataBaseHelper(bookContext)
                val writer = db.writableDatabase

                writer.delete(TBL_BOOK_RECORD, "$BOOKING_ID=?", arrayOf(bookId.toString()))
            }

            fun updateBooking(book: BookingModel): Int {
                val db = DataBaseHelper(bookContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(BOOKING_ID, book.bookingId)
                contentValues.put(ID, book.memId)
                contentValues.put(CONSULTANT_ID, book.consultantId)
                contentValues.put(TIME_SELECTED, book.timeSelected)
                contentValues.put(DATE_SELECTED, book.dateSelected)

                val success = db.update(
                    TBL_BOOK_RECORD,
                    contentValues,
                    "$BOOKING_ID= ${book.bookingId}",
                    null
                )
                db.close()
                return success
            }

            fun getAllTime(consultant: Int, memID: Int, dateSelected: String): List<String> {
                val timeList: ArrayList<String> = ArrayList()
                Log.d("DBHELPER", "getAllTime Called, $consultant, $memID, $dateSelected")
                val selectQuery =
                    "SELECT * FROM $TBL_BOOK_RECORD WHERE $DATE_SELECTED = '$dateSelected' AND ($CONSULTANT_ID = $consultant OR $ID = $memID)"
                val db = DataBaseHelper(bookContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var time: String

                if (cursor.moveToFirst()) {
                    do {
                        time = cursor.getString(cursor.getColumnIndexOrThrow(TIME_SELECTED))

                        timeList.add(time)
                        Log.d("DBHELPER", "$time is chosen")
                    } while (cursor.moveToNext())
                }
                return timeList
            }
        }

        class ConsultantTable(context: Context) {

            private val consultantContext: Context = context
            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("Create Table $TBL_CONSULTANT ($CONSULTANT_ID INTEGER PRIMARY KEY,$CONSULTANT_NAME TEXT)")
                db.execSQL(createQuery)
            }

            fun insertConsultant(consultant: Consultant): Long {
                val db = DataBaseHelper(consultantContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(CONSULTANT_ID, consultant.consultantId)
                contentValues.put(CONSULTANT_NAME, consultant.name)
                return db.insert(TBL_CONSULTANT, null, contentValues)
            }

            fun getAllNames(): List<String> {
                val nameList: ArrayList<String> = ArrayList()
                val selectQuery =
                    "SELECT * FROM $TBL_CONSULTANT"
                val db = DataBaseHelper(consultantContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var name: String

                if (cursor.moveToFirst()) {
                    do {
                        name = cursor.getString(cursor.getColumnIndexOrThrow(CONSULTANT_NAME))

                        nameList.add(name)
                    } while (cursor.moveToNext())
                }
                return nameList
            }

            fun getName(conId: Int): String {
                val selectQuery =
                    "SELECT * FROM $TBL_CONSULTANT WHERE $CONSULTANT_ID = $conId"
                val db = DataBaseHelper(consultantContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ""
                }

                if (cursor.moveToFirst()) {
                    return cursor.getString(cursor.getColumnIndexOrThrow(CONSULTANT_NAME))
                }
                return ""
            }

            fun getId(consultant: Int): Int {
                val selectQuery =
                    "SELECT * FROM $TBL_CONSULTANT LIMIT 1 OFFSET $consultant"
                val db = DataBaseHelper(consultantContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return -1
                }


                if (cursor.moveToFirst()) {
                    return cursor.getInt(cursor.getColumnIndexOrThrow(CONSULTANT_ID))
                }
                return -1
            }
        }

        class GuideTable(context: Context) {
            private val guideContext: Context = context

            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_GUIDE($GUIDE_ID INTEGER PRIMARY KEY,$GUIDE_IMAGE INT,$GUIDE_TITLE TEXT,$GUIDE_METHOD TEXT,$GUIDE_CONTENT TEXT)")
                db.execSQL(createQuery)
            }

            fun insertGuideDetail(guide: GuideData): Long {
                val db = DataBaseHelper(guideContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(GUIDE_ID, guide.id)
                contentValues.put(GUIDE_IMAGE, guide.image)
                contentValues.put(GUIDE_TITLE, guide.vegetableName)
                contentValues.put(GUIDE_METHOD, guide.method)
                contentValues.put(GUIDE_CONTENT, guide.veg_content)
                return db.insert(TBL_GUIDE, null, contentValues)
            }

            fun getDetail(VegID: Int): GuideData {
                val selectQuery = "SELECT * FROM $TBL_GUIDE WHERE $GUIDE_ID=$VegID"
                val db = DataBaseHelper(guideContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return GuideData(0, "", 0, "", "")
                }

                val vegId: Int
                val image: Int
                val guideTitle: String
                val method: String
                val content: String

                if (cursor.moveToFirst()) {
                    vegId = cursor.getInt(cursor.getColumnIndexOrThrow(GUIDE_METHOD))
                    image = cursor.getInt(cursor.getColumnIndexOrThrow(GUIDE_IMAGE))
                    guideTitle = cursor.getString(cursor.getColumnIndexOrThrow(GUIDE_TITLE))
                    method = cursor.getString(cursor.getColumnIndexOrThrow(GUIDE_METHOD))
                    content = cursor.getString(cursor.getColumnIndexOrThrow(GUIDE_CONTENT))

                    return GuideData(vegId, guideTitle, image, method, content)
                }
                return GuideData(0, "", 0, "", "")
            }

            fun getAllGuides(): List<GuideData> {
                val guideList: ArrayList<GuideData> = ArrayList()
                val selectQuery =
                    "SELECT * FROM $TBL_GUIDE"
                val db = DataBaseHelper(guideContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var id: Int
                var vegetableName: String
                var image: Int
                var method: String
                var content: String

                if (cursor.moveToFirst()) {
                    do {
                        id = cursor.getInt(cursor.getColumnIndexOrThrow(GUIDE_ID))
                        vegetableName = cursor.getString(cursor.getColumnIndexOrThrow(GUIDE_TITLE))
                        image = cursor.getInt(cursor.getColumnIndexOrThrow(GUIDE_IMAGE))
                        method = cursor.getString(cursor.getColumnIndexOrThrow(GUIDE_METHOD))
                        content = cursor.getString(cursor.getColumnIndexOrThrow(GUIDE_CONTENT))

                        val guideData = GuideData(id, vegetableName, image, method, content)
                        guideList.add(guideData)
                    } while (cursor.moveToNext())
                }
                return guideList
            }
        }

        class BorrowTable(context: Context) {
            private val borrowContext: Context = context

            fun createTable(db: SQLiteDatabase) {
                val createQuery =
                    ("CREATE TABLE $TBL_BORROW($BORROW_ID INTEGER PRIMARY KEY,$WEBSITE_NAME TEXT,$COUNTRY TEXT,$URL_DES TEXT)")
                db.execSQL(createQuery)
            }

            fun insertBorrowDetail(borrow: BorrowData): Long {
                val db = DataBaseHelper(borrowContext).writableDatabase
                val contentValues = ContentValues()
                contentValues.put(BORROW_ID, getNextId(borrowContext, TBL_BORROW, BORROW_ID))
                contentValues.put(WEBSITE_NAME, borrow.organizationName)
                contentValues.put(COUNTRY, borrow.country)
                contentValues.put(URL_DES, borrow.url)
                return db.insert(TBL_BORROW, null, contentValues)

            }

            fun getAllBorrowDetail(country: String): List<BorrowData> {
                val borrowList: ArrayList<BorrowData> = ArrayList()
                val selectQuery = "SELECT * FROM $TBL_BORROW WHERE $COUNTRY ='$country'"
                val db = DataBaseHelper(borrowContext).readableDatabase

                val cursor: Cursor?

                try {
                    cursor = db.rawQuery(selectQuery, null)
                } catch (e: Exception) {
                    e.printStackTrace()
                    db.execSQL(selectQuery)
                    return ArrayList()
                }

                var webName: String
                var countryName: String
                var urlDes: String

                if (cursor.moveToFirst()) {
                    do {
                        webName = cursor.getString(cursor.getColumnIndexOrThrow(WEBSITE_NAME))
                        countryName = cursor.getString(cursor.getColumnIndexOrThrow(COUNTRY))
                        urlDes = cursor.getString(cursor.getColumnIndexOrThrow(URL_DES))
                        val borrow = BorrowData(countryName, webName, urlDes)
                        borrowList.add(borrow)
                    } while (cursor.moveToNext())
                }
                return borrowList

            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // create tables
        MemberTable(CONTEXT).createTable(db)
        ForumTable(CONTEXT).createTable(db)
        ForumLikeTable(CONTEXT).createTable(db)
        ForumCommentTable(CONTEXT).createTable(db)
        ForumReportTable(CONTEXT).createTable(db)
        CommentLikeTable(CONTEXT).createTable(db)
        ConsultantTable(CONTEXT).createTable(db)
        BookRecordTable(CONTEXT).createTable(db)
        GuideTable(CONTEXT).createTable(db)
        BorrowTable(CONTEXT).createTable(db)
//        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        return
    }

}