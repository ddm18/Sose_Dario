<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f5f5f5;
        }
        h1 {
            text-align: center;
        }
        .form-container {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 15px;
            margin: 20px auto;
            max-width: 600px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .form-container select {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
        }
        .form-container button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 15px;
            cursor: pointer;
            display: block;
            width: 100%;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        .selected-hotels {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 15px;
            margin: 20px auto;
            max-width: 600px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .hotel-item {
            margin: 10px 0;
            text-align: center;
        }
        .between-hotels-button {
            margin: 10px 0;
            text-align: center;
        }
        .btn {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 3px;
            text-align: center;
            margin: 10px 0;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .delete-button {
            display: block;
            margin: 20px auto;
            text-align: center;
            background-color: #dc3545;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            max-width: 200px;
        }
        .delete-button:hover {
            background-color: #c82333;
        }
        .delete-booking-btn {
		    background-color: #dc3545; /* Red background */
		    color: #fff; /* White text */
		}
		
		.delete-booking-btn:hover {
		    background-color: #c82333; /* Darker red on hover */
		}

    </style>
</head>
<body>
    <h1>${title}</h1>

    <div class="form-container">
        <form action="/hotels" method="post">
            <label for="hotel">Select a Hotel:</label>
            <select name="selectedHotelId" id="hotel">
                <#list allHotels as hotel>
                    <#assign isAlreadyBooked = false>
                    <#if bookings?has_content>                    
                        <#list bookings as booking>
                            <#if booking.id == hotel.id>
                                <#assign isAlreadyBooked = true>
                            </#if>
                        </#list>
                    </#if>
                    <#if !isAlreadyBooked>
                        <option value="${hotel.id}">
                            ${hotel.hotelName} - ${hotel.locationName}
                        </option>
                    </#if>
                </#list>
            </select>
            <button type="submit">Add Hotel</button>
        </form>
    </div>

	<div class="selected-hotels">
	    <h2>Selected Hotels</h2>
	    <ul>
	        <#if bookings?has_content>
	            <#list bookings as booking>
	                <li class="hotel-item">
	                    <p>${booking.hotelName} - ${booking.locationName}</p>
	                </li>
	                <!-- Add a button if this is not the last item -->
	                <div class="between-hotels-button">
					<#if booking?has_next>
				        <a href="/hotels/transportation?hotel1=${booking.id}&hotel2=${bookings[booking?index + 1].id}" class="btn">
				            Find Transport
				        </a>
					</#if>
						<form action="/delete-itinerary" method="post" style="display: inline;">
						    <input type="hidden" name="hotel_to_delete" value="${booking.id}">
						    <a href="#" onclick="this.closest('form').submit();" class="btn delete-booking-btn">Delete booking</a>
						</form>
				    </div>
	            </#list>
	        <#else>
	            <p>No hotels selected yet.</p>
	        </#if>
	    </ul>
	</div>
	
	

    <!-- Add delete button -->
    <form action="/delete-itinerary" method="post" style="text-align: center;">
        <button type="submit" class="delete-button">Delete All Itineraries</button>
    </form>
</body>
</html>
