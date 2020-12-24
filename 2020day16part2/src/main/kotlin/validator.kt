package main.kotlin


fun validateNearby(nearbyNums: List<Int>, fields: List<Field>): Boolean {
    return nearbyNums.firstOrNull { num ->
        fields.firstOrNull { f ->
            f.ranges.firstOrNull { p -> num >= p.first && num <= p.second } != null
        } != null
    } != null
}