package com.example.gd.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {BreakFast.class, Lunch.class, Dinner.class, Snack.class}, version = 1, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {

    public abstract MealDAO breakFastDAO();

    public abstract LunchDAO lunchDAO();

    public abstract SnackDAO snackDao();

    public abstract DinnerDAO dinnerDao();

    private static MealDatabase INSTANCE;

    static MealDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MealDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, "diet_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MealDAO mDao;

        private final LunchDAO lunchDao;
        private final SnackDAO snackDao;
        private final DinnerDAO dinnerDao;

        PopulateDbAsync(MealDatabase db) {
            mDao = db.breakFastDAO();
            lunchDao = db.lunchDAO();
            snackDao = db.snackDao();
            dinnerDao = db.dinnerDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();
            lunchDao.deleteAll();
            snackDao.deleteAll();
            dinnerDao.deleteAll();
            mDao.insertAll(new BreakFast(1, "1 egg ||1 slice cheese or 1/4 slice avocado", "p", 0));
            mDao.insertAll(new BreakFast(1, "1 whole grain English muffin", "c", 2));
            mDao.insertAll(new BreakFast(1, "6 ounces plain yogurt", "c", 1));
            mDao.insertAll(new BreakFast(2, "1 slice of sprouted whole grain bread", "c", 1));
            mDao.insertAll(new BreakFast(2, "6 ounces plain yogurt", "c", 1));
            mDao.insertAll(new BreakFast(2, "1 small muffin", "c", 1));
            mDao.insertAll(new BreakFast(2, "1  egg", "p", 0));
            mDao.insertAll(new BreakFast(3, "6 small breadsticks(4” long)", "c", 1));
            mDao.insertAll(new BreakFast(3, "1/2 English muffin", "c", 1));
            mDao.insertAll(new BreakFast(3, "1 cup plain yogurt", "c", 1));
            mDao.insertAll(new BreakFast(3, "1 egg ||suger-free-jell-o||1 slice cheese", "p", 0));
            mDao.insertAll(new BreakFast(4, "1/2 small bagel (or) ¼ deli bagel", "c", 1));
            mDao.insertAll(new BreakFast(4, "1/2 cup bran cereal", "c", 1));
            mDao.insertAll(new BreakFast(4, "1/2 cup evaporated milk", "c", 1));
            mDao.insertAll(new BreakFast(4, "1 egg ||1 slice cheese", "p", 0));
            mDao.insertAll(new BreakFast(5, "1/2 hot dog (or) hamburger bun", "c", 1));
            mDao.insertAll(new BreakFast(5, "1 pancake (or) waffle(5’’)", "c", 1));
            mDao.insertAll(new BreakFast(5, "1/3 cup dry milk powder", "c", 1));
            mDao.insertAll(new BreakFast(5, "1 egg ||suger-free-jell-o||1 slice cheese", "p", 0));
            mDao.insertAll(new BreakFast(6, "1/2 cup bran cereal", "c", 1));
            mDao.insertAll(new BreakFast(6, "1 small muffin", "c", 1));
            mDao.insertAll(new BreakFast(6, "1/2 cup applesauce", "c", 0.5f));
            mDao.insertAll(new BreakFast(6, "2 fig cookies", "c", 0.5f));
            mDao.insertAll(new BreakFast(6, "1/4 cup nuts", "p", 0));
            mDao.insertAll(new BreakFast(7, "1/2 cup granola", "c", 1));
            mDao.insertAll(new BreakFast(7, "1 pot pie(7oz)", "c", 2));
            mDao.insertAll(new BreakFast(7, "1 hardboiled egg", "p", 0));
            mDao.insertAll(new BreakFast(7, "2 tbsp peanut butter", "p", 0));
            mDao.insertAll(new BreakFast(8, "1/2  cup cooked cereal", "c", 1));
            mDao.insertAll(new BreakFast(8, "1 small enchilada", "c", 2));
            mDao.insertAll(new BreakFast(8, "1/4 cup tuna (or) egg salad", "p", 0));
            mDao.insertAll(new BreakFast(8, "1 slice cheese", "p", 0));
            mDao.insertAll(new BreakFast(9, "1/2  cup sweetened cereal", "c", 1));
            mDao.insertAll(new BreakFast(9, "1 cup chocolate milk", "c", 2));
            mDao.insertAll(new BreakFast(9, "1/4 cup nuts", "p", 0));
            mDao.insertAll(new BreakFast(9, "1 hardboiled egg", "p", 0));
            mDao.insertAll(new BreakFast(10, "1 plain donut", "c", 1));
            mDao.insertAll(new BreakFast(10, "1/8  of a pumpkin pie", "c", 1));
            mDao.insertAll(new BreakFast(10, "1 small granola bar", "c", 1));
            mDao.insertAll(new BreakFast(10, "hot cocoa", "p", 0));
            mDao.insertAll(new BreakFast(10, "2 tbsp sunflower seeds", "p", 0));
            lunchDao.insertAll(new Lunch(1, "2 slices whole grain bread", "c", 2));
            lunchDao.insertAll(new Lunch(1, "3 ounces chicken breast", "p", 0));
            lunchDao.insertAll(new Lunch(1, "1 tbsp mustard", "p", 0));
            lunchDao.insertAll(new Lunch(1, "1 cup carrot sticks", "p", 0));
            lunchDao.insertAll(new Lunch(1, "1 small apple", "c", 1));
            lunchDao.insertAll(new Lunch(2, "2 soft tacos", "c", 2));
            lunchDao.insertAll(new Lunch(2, "1 cup cream based soup", "c", 1));
            lunchDao.insertAll(new Lunch(2, "ham or turkey", "p", 0));
            lunchDao.insertAll(new Lunch(2, "1/2 cup carrots and celery", "p", 0));
            lunchDao.insertAll(new Lunch(3, "1 regular slice pizza", "c", 2));
            lunchDao.insertAll(new Lunch(3, "3 ounces chicken breast", "p", 0));
            lunchDao.insertAll(new Lunch(3, "1 hardboiled egg", "p", 0));
            lunchDao.insertAll(new Lunch(3, "pork or ham", "p", 0));
            lunchDao.insertAll(new Lunch(3, "1 small apple", "c", 1));
            lunchDao.insertAll(new Lunch(4, "2  stuffed cannelloni", "c", 2));
            lunchDao.insertAll(new Lunch(4, "1 cup broth(chicken or beef with noodles)", "c", 1));
            lunchDao.insertAll(new Lunch(4, "1/2 cup carrots and celery", "p", 0));
            lunchDao.insertAll(new Lunch(5, "2/3 cup macaroni & cheese", "c", 2));
            lunchDao.insertAll(new Lunch(5, "1/2 cup bean,split pea or lentil", "c", 1));
            lunchDao.insertAll(new Lunch(5, "1 hardboiled egg", "p", 0));
            lunchDao.insertAll(new Lunch(5, "1/2 cup carrots and celery", "p", 0));
            lunchDao.insertAll(new Lunch(6, "1 cup beef stew", "c", 2));
            lunchDao.insertAll(new Lunch(6, "1 cup cream based soup", "c", 1));
            lunchDao.insertAll(new Lunch(6, "1/4 cup tuna or egg salad", "p", 0));
            lunchDao.insertAll(new Lunch(6, "1/2cup broccoli and beans", "p", 0));
            lunchDao.insertAll(new Lunch(7, "1 meat burrito", "c", 2));
            lunchDao.insertAll(new Lunch(7, "1 cup cream based soup", "c", 1));
            lunchDao.insertAll(new Lunch(7, "1 hardboiled egg", "p", 0));
            lunchDao.insertAll(new Lunch(7, "sugar-free-jell-o", "p", 0));
            lunchDao.insertAll(new Lunch(7, "diet soda", "p", 0));
            lunchDao.insertAll(new Lunch(8, "1 small baked potato", "c", 1));
            lunchDao.insertAll(new Lunch(8, "3 ounces chicken breast", "p", 0));
            lunchDao.insertAll(new Lunch(8, "1/3 cup cooked pasta (or) rice", "c", 1));
            lunchDao.insertAll(new Lunch(8, "1/2 cup asparagus,cabbage", "p", 0));
            lunchDao.insertAll(new Lunch(8, "1 small apple", "c", 1));
            lunchDao.insertAll(new Lunch(9, "1/2 cupked grains", "c", 1));
            lunchDao.insertAll(new Lunch(9, "3 ounces chicken breast", "p", 0));
            lunchDao.insertAll(new Lunch(9, "2 soft tacos", "c", 2));
            lunchDao.insertAll(new Lunch(9, "1 cup carrot sticks", "p", 0));
            lunchDao.insertAll(new Lunch(10, "2/3 cup macaroni & cheese", "c", 2));
            lunchDao.insertAll(new Lunch(10, "3 medium prunes", "c", 1));
            lunchDao.insertAll(new Lunch(10, "1 slice ham (or) turkey", "p", 0));
            lunchDao.insertAll(new Lunch(10, "1 hardboiled egg", "p", 0));
            lunchDao.insertAll(new Lunch(10, "diet soda", "p", 0));
            lunchDao.insertAll(new Lunch(11, "1 cup ravioli", "c", 2));
            lunchDao.insertAll(new Lunch(11, "3 ounces chicken breast", "p", 0));
            lunchDao.insertAll(new Lunch(11, "1/2 cup corn (or)green peas", "c", 1));
            lunchDao.insertAll(new Lunch(11, "sugar-free-jell-o", "p", 0));
            lunchDao.insertAll(new Lunch(12, "1/3  cup cooked rice", "c", 1));
            lunchDao.insertAll(new Lunch(12, "1 cup beef stew", "c", 2));
            lunchDao.insertAll(new Lunch(12, "1/2 cup carrots and celery", "p", 0));
            lunchDao.insertAll(new Lunch(12, "1 egg", "p", 0));
            lunchDao.insertAll(new Lunch(12, "sugar-free-popsicles", "p", 0));
            lunchDao.insertAll(new Lunch(13, "1 cup chicken noodle soup  ravioli", "c", 2));
            lunchDao.insertAll(new Lunch(13, "1 string cheese", "p", 0));
            lunchDao.insertAll(new Lunch(13, "1/2 cup cauliflower (or)green peas", "p", 0));
            lunchDao.insertAll(new Lunch(13, "1/2 cup ice cream (or) ice milk", "c", 1));
            lunchDao.insertAll(new Lunch(14, "1 corn tortilla(6’’)", "c", 0.5f));
            lunchDao.insertAll(new Lunch(14, "1/2 cup cooked potatoes", "c", 1));
            lunchDao.insertAll(new Lunch(14, "1 large banana", "c", 0.5f));
            lunchDao.insertAll(new Lunch(14, "1 small apple", "c", 1));
            lunchDao.insertAll(new Lunch(15, "1/3 cup cooked pasta", "c", 1));
            lunchDao.insertAll(new Lunch(15, "1 small baked potato", "c", 1));
            lunchDao.insertAll(new Lunch(15, "1 cup broth(chicken or beef with noodles)", "c", 1));
            lunchDao.insertAll(new Lunch(15, "2 tbsp sunflower seeds", "p", 0));
            snackDao.insertAll(new Snack(1, "1 medium apple", "c", 1));
            snackDao.insertAll(new Snack(2, "2 rice cakes", "c", 1));
            snackDao.insertAll(new Snack(3, "1 cereal bar (or) granola bar", "c", 1));
            snackDao.insertAll(new Snack(4, "2 small oatmeal cookies", "c", 1));
            snackDao.insertAll(new Snack(5, "1/2 cup sugar free puding", "c", 1));
            snackDao.insertAll(new Snack(6, "1/2 cup ice cream", "c", 1));
            snackDao.insertAll(new Snack(7, "2 fig cookies", "c", 1));
            snackDao.insertAll(new Snack(8, "6 oz yogurt", "c", 1));
            snackDao.insertAll(new Snack(9, "8 animal crackers", "c", 1));
            snackDao.insertAll(new Snack(10, "4 cups popcorn", "c", 1));
            snackDao.insertAll(new Snack(11, "4 round crackers", "c", 1));
            snackDao.insertAll(new Snack(12, "3/4 oz pretzels", "c", 1));
            snackDao.insertAll(new Snack(13, "1 oz snack chips", "c", 1));
            snackDao.insertAll(new Snack(14, "6 saltine crackers", "c", 1));
            snackDao.insertAll(new Snack(15, "2’’*2’’ piece of cake (no icing)", "c", 1));
            snackDao.insertAll(new Snack(16, "2 small cookies", "c", 1));
            snackDao.insertAll(new Snack(17, "5 vanilla waffers", "c", 1));
            snackDao.insertAll(new Snack(18, "2’’ brownie", "c", 1));
            snackDao.insertAll(new Snack(19, "1/2 custard", "c", 1));
            snackDao.insertAll(new Snack(20, "1 plain donut", "c", 1));
            snackDao.insertAll(new Snack(21, "1/8  of a pumpkin pie", "c", 1));
            snackDao.insertAll(new Snack(22, "1/2  of a twin popsicle bar", "c", 1));
            snackDao.insertAll(new Snack(23, "2 tbsp light maple syrup", "c", 1));
            snackDao.insertAll(new Snack(24, "10-15 french fries", "c", 1));
            snackDao.insertAll(new Snack(25, "1/2  cup cooked grains", "c", 1));
            dinnerDao.insertAll(new Dinner(1, "1/16  piece of any double crust pie", "c", 3));
            dinnerDao.insertAll(new Dinner(1, "1 slice ham or turkey", "p", 0));
            dinnerDao.insertAll(new Dinner(1, "1 egg", "p", 0));
            dinnerDao.insertAll(new Dinner(1, "sugar-free-jell-o", "p", 0));
            dinnerDao.insertAll(new Dinner(2, "2 slice whole wheat toast with 1tbsp nutella", "c", 3));
            dinnerDao.insertAll(new Dinner(2, "cut up vegetables and dip", "p", 0));
            dinnerDao.insertAll(new Dinner(2, "1 egg", "p", 0));
            dinnerDao.insertAll(new Dinner(2, "1/4 cup nuts", "p", 0));
            dinnerDao.insertAll(new Dinner(3, "1(2oz) lender’s bagel (or) 2 mini bagel", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(3, "1 cut trail mix", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(3, "1 small baked potato", "c", 1));
            dinnerDao.insertAll(new Dinner(3, "1 small apple", "c", 1));
            dinnerDao.insertAll(new Dinner(4, "1/3 cup cooked pasta", "c", 1));
            dinnerDao.insertAll(new Dinner(4, "1 small muffin", "c", 1));
            dinnerDao.insertAll(new Dinner(4, "1/2 cup cooked potatoes", "c", 1));
            dinnerDao.insertAll(new Dinner(4, "1 slice cheese", "p", 0));
            dinnerDao.insertAll(new Dinner(5, "1 regular slice of pizza", "c", 2));
            dinnerDao.insertAll(new Dinner(5, "1 cut plain yogurt", "c", 1));
            dinnerDao.insertAll(new Dinner(5, "1 slice ham or turkey", "p", 0));
            dinnerDao.insertAll(new Dinner(5, "sugar-free-jell-o", "p", 0));
            dinnerDao.insertAll(new Dinner(6, "1 meat burrito", "c", 2));
            dinnerDao.insertAll(new Dinner(6, "10-15 french fries", "c", 1));
            dinnerDao.insertAll(new Dinner(6, "4 oz cottage cheese", "p", 0));
            dinnerDao.insertAll(new Dinner(6, "sugar-free-jell-o", "p", 0));
            dinnerDao.insertAll(new Dinner(7, "2 slices light bread", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(7, "2 fig cookies", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(7, "2/3 cup macaroni & cheese", "c", 2));
            dinnerDao.insertAll(new Dinner(7, "1 egg", "p", 0));
            dinnerDao.insertAll(new Dinner(8, "8oz soy milk with 5 vanilla waffers", "c", 3));
            dinnerDao.insertAll(new Dinner(8, "1/4 cup nuts", "p", 0));
            dinnerDao.insertAll(new Dinner(8, "2 tbsp sunflower seeds", "p", 0));
            dinnerDao.insertAll(new Dinner(8, "sugar-free-jell-o", "p", 0));
            dinnerDao.insertAll(new Dinner(9, "1/2 cup sugar free pudding", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(9, "10 oz milk", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(9, "1 corn tortilla(6’’)", "c", 0.5f));
            dinnerDao.insertAll(new Dinner(9, "1 plain donut", "c", 1));
            dinnerDao.insertAll(new Dinner(9, "2 fig cookies", "c", 0.5f));
            return null;
        }
    }

}

