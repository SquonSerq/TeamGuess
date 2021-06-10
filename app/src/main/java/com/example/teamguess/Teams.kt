package com.example.teamguess

object Teams {
    val names = mutableListOf<String>()
    val teamsList = mutableListOf<Team>()

    fun prepareTeamsList(){
        names.shuffle()
        val chunkedPeople = names.chunked(2)

        for(i in chunkedPeople){
            teamsList.add(Team("${i[0]}/${i[1]}"))
        }

        GameCycle.teamNum = teamsList.size
    }

    fun getAllTeamsNames(): List<String>{
        var allTeamNames = mutableListOf<String>()
        for(i in teamsList){
            allTeamNames.add("${i.name} : ${i.rightAnswers.size}")
        }
        return allTeamNames.toList()
    }

    fun getPeopleNames(): List<String>{
        return names.toList()
    }

}
