package main.kotlin


fun validateNearby(nearbyNums: List<Int>, fields: List<Field>): Boolean {
    return nearbyNums.all { num ->
        fields.any { f ->
            f.ranges.any { p -> num >= p.first && num <= p.second }
        }
    }
}