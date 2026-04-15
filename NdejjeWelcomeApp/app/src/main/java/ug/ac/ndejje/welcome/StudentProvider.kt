import ug.ac.ndejje.welcome.R
import ug.ac.ndejje.welcome.Student

class StudentProvider {
    companion object {
        val studentList = listOf(
            Student(1, "Akello Stellamaris", "24/2/314/01", "BIT", R.drawable.female_1, true),
            Student(2, "Kirya James",       "24/2/314/02", "BCS", R.drawable.male_1,     false),
            Student(3, "Mbabazi Joan",      "24/2/314/03", "BIT", R.drawable.female_3, true),
            Student(4, "Allu Samantha", "24/2/314/01", "BIT", R.drawable.female_13, true),
            Student(5, "Kira light",       "24/2/314/02", "BCS", R.drawable.male_13,     false),
            Student(6,"Mani joab",      "24/2/314/03", "BIT", R.drawable.male_12, true),
            Student(7, "waswa sam", "24/2/314/01", "BIT", R.drawable.female_14, true),
            Student(8, "Kizito jake",       "24/2/314/02", "BCS", R.drawable.female_16,     false),
            Student(9, "Mbakini kaiju",      "24/2/314/03", "BIT", R.drawable.female_15, true),
            Student(10, "Kota Jone",     "24/2/314/04", "BSE", R.drawable.male_11,     true)
        )
    }
}