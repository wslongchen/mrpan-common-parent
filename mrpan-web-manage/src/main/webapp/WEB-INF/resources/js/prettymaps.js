/*
* prettyMaps 1.0.0
*
* Copyright 2014, Jean-Marc Goepfert - http://omgoepfert.com
* Released under the WTFPL license - http://www.wtfpl.net/
*
* Date: Sun Jan 12 18:00:00 2014 -0500
*/

(function($) {

    function prettyMaps(el, options) {

        options = options || {};

        this.defaults = {
            address: ['Melbourne, Australia'],
            zoom: 12,
            panControl: false,
            zoomControl: false,
            mapTypeControl: false,
            scaleControl: false,
            streetViewControl: false,
            overviewMapControl: false,
            scrollwheel: true,
            image: '',
            styles: [
                {
                    stylers: [
                        { hue: options.hue },
                        { saturation: options.saturation },
                        { lightness: options.lightness }
                    ]
                }
            ]
        };

        this.options = $.extend({}, this.defaults, options);
        if( typeof this.options.address === 'string' ) { this.options.address = [ this.options.address ]; }
        this.$el = $(el);
    }

    prettyMaps.prototype = {

        init: function() {
            var that = this,
                geocoder = new google.maps.Geocoder(),
                locations = [],
                map;

            for (var i = 0; i < this.options.address.length; i++) {
                geocoder.geocode({
                    'address': this.options.address[i]
                }, function(results, status) {
                    if ( status === google.maps.GeocoderStatus.OK ) {

                        locations.push(results);
                        if (locations.length === 1) {
                            map = that.drawMap(locations[locations.length - 1]);
                        }
                        var marker = that.placeMarker(map, results);
                    }
                });
            }
        },

        drawMap: function(results) {
            var mapDetails = { center: results[0].geometry.location },
                finalOptions = $.extend({}, this.options, mapDetails),
                map = new google.maps.Map(this.$el[0], finalOptions);

            return map;
        },

        placeMarker: function(map, results) {
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location,
                icon: this.options.image,
                animation: google.maps.Animation.DROP
            });
        }
    };

    $.fn.prettyMaps = function(options) {
        if ( this.length ) {
            this.each(function() {
                var rev = new prettyMaps(this, options);
                rev.init();
                $(this).data('prettyMaps', rev);
            });
        }
    };
})(jQuery);
