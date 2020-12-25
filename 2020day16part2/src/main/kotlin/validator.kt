package main.kotlin


fun validateNearby(nearbyNums: List<Int>, fields: List<Field>): Boolean {
    return nearbyNums.all {
        fields.any { f ->
            f.ranges.any { p -> it >= p.first && it <= p.second }
        }
    }
}