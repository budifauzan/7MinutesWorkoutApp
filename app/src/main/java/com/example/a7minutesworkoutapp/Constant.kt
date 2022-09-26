package com.example.a7minutesworkoutapp

object Constant {
    fun getExercises(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()
        exerciseList.add(
            ExerciseModel(
                1, "Abdominal Crunch", R.drawable.ic_abdominal_crunch
            )
        )
        exerciseList.add(
            ExerciseModel(
                2, "High Knees Running in Place", R.drawable.ic_high_knees_running_in_place
            )
        )
        exerciseList.add(
            ExerciseModel(
                3, "Jumping Jacks", R.drawable.ic_jumping_jacks
            )
        )
        exerciseList.add(
            ExerciseModel(
                4, "Lunge", R.drawable.ic_lunge
            )
        )
        exerciseList.add(
            ExerciseModel(
                5, "Plank", R.drawable.ic_plank
            )
        )
        exerciseList.add(
            ExerciseModel(
                6, "Push Up", R.drawable.ic_push_up
            )
        )
        exerciseList.add(
            ExerciseModel(
                7, "Push Up and Rotation", R.drawable.ic_push_up_and_rotation
            )
        )
        exerciseList.add(
            ExerciseModel(
                8, "Side Plank", R.drawable.ic_side_plank
            )
        )
        exerciseList.add(
            ExerciseModel(
                9, "Squat", R.drawable.ic_squat
            )
        )
        exerciseList.add(
            ExerciseModel(
                10, "Step Up onto Chair", R.drawable.ic_step_up_onto_chair
            )
        )
        exerciseList.add(
            ExerciseModel(
                11, "Triceps Dip on Chair", R.drawable.ic_triceps_dip_on_chair
            )
        )
        exerciseList.add(
            ExerciseModel(
                12, "Wall Sit", R.drawable.ic_wall_sit
            )
        )
        return exerciseList
    }
}