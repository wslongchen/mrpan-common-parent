;(function ( $, window, document, undefined ) {

	'use strict';

	var pluginName = "barChart";

	function Plugin( element, options ) {

		var defaults = {
			bars : [],
			hiddenBars : [],
			vertical : false,
			colors : [
				"#f44336", "#e91e63", "#9c27b0", "#673ab7", "#3f51b5",
				"#2196f3", "#03a9f4", "#00bcd4", "#009688", "#4caf50",
				"#8bc34a", "#cddc39", "#ffeb3b", "#ffc107", "#ff9800",
				"#ff5722", "#795548", "#9e9e9e", "#607d8b", "#263238"
			],
			barGap : 5,
			stepsCount : 5,
			defaultWidth : 40,
			totalSumHeight : 25,
			defaultColumnWidth : 65
		};

		this.element = element;

		this.options = $.extend( {}, defaults, options );

		this._defaults = defaults;

		this._name = pluginName;

		this.init();
	}

	Plugin.prototype = {

		init: function() {


			$(this.element)
				.css( 'height', this.options.height && !this.options.vertical ? this.options.height : 'auto' )
				.addClass('bar-chart')
				.addClass( this.options.vertical ? 'bar-chart-vertical' : '' )
				.wrap('<div class="bar-chart-wrapper"></div>');


			this.options.maxHeight =
				this.options.vertical ?
					( $(this.element).width() ) :
					( $(this.element).height() - this.options.totalSumHeight );

			this.options.maxWidth =
				this.options.vertical ?
					( this.options.defaultWidth ) :
					( $(this.element).width() );


			this.options.barGapPercent = this.options.barGap / (this.options.maxWidth / 100);

			this.options.bars = this.colorizeBars(this.options.bars, this.options.colors);

			this.options.columns = this.groupByKey(this.options.bars, this.options.hiddenBars);


			this.drawY(this.element, this.options);

			this.drawX(this.element, this.options);

			this.drawToolTip(this.element, this.options);

			this.drawLegend(this.element, this.options);

			this.subscribe(this.element, this.options);


			return this;
		},

		update : function(el, options) {


			$(el).find('.bar, .bar-y, .bar-x').remove();


			options.columns = this.groupByKey(options.bars, options.hiddenBars);


			this.drawY(el, options);

			this.drawX(el, options);

			this.drawToolTip(el, options);


			return this;
		},

		groupByKey : function(bars, hiddenBars){

			hiddenBars = hiddenBars || [];

			var columns = {};

			bars.forEach(function(bar){

				if (hiddenBars.indexOf(bar.name) !== -1) {
					return true;
				}

				bar.values.forEach(function(value){
					columns[ value[0] ] = columns[ value[0] ] || [];
					columns[ value[0] ].push({ value : parseFloat(value[1]), name : bar.name, color : bar.color });
				});

			});

			return columns;
		},

		colorizeBars : function(bars, colors){

			var colorIndex = 0;

			bars.forEach(function(bar){

				if (typeof bar.color === 'undefined') {
					bar.color = colors[colorIndex];
				}

				colorIndex++;

				if (colorIndex >= colors.length) {
					colorIndex = 0;
				}

			});

			return bars;
		},

		findMax : function(columns){

			var result = 0;

			for (var i in columns) {

				if (columns.hasOwnProperty(i)) {

					var max = 0;

					columns[i].forEach(function(value){
						max += value.value;
					});

					if (max > result) {
						result = max;
					}

				}

			}

			return result;
		},

		totalSum : function(columns){

			var result = 0;

			for (var i in columns) {

				if (columns.hasOwnProperty(i)) {

					columns[i].forEach(function(value){
						result += value.value;
					});

				}

			}

			return result;
		},

		formatDate : function(date){

			var dd = date.getDate();
			var mm = date.getMonth() + 1;
			var yyyy = date.getFullYear().toString().substring(2);

			return [ dd, mm, yyyy ].join('.');
		},

		drawY: function(el, options) {

			var container = document.createElement('div');

			var max = this.findMax(options.columns);

			var milestonesCount = Math.round( max ).toString().length;

			var multiplier = Math.pow(10, milestonesCount - 1);

			container.classList.add( options.vertical ? 'bar-x' : 'bar-y' );

			max = options.vertical ? Math.ceil(max) : Math.ceil(max / multiplier) * multiplier;

			var step = (max / options.stepsCount);

			if (step < 1) {
				step = 1;
			}

			var top = 0;
			var value = 0;

			var yClassName = options.vertical ? 'bar-x-value' : 'bar-y-value';
			var yPropertyName = options.vertical ? 'left' : 'bottom';

			while (top < options.maxHeight) {

				top = (value * options.maxHeight) / max;

				var gridValue = value;

				if (gridValue < 1000) {
					gridValue = gridValue.toFixed(2);
				}

				if (gridValue >= 1000 && gridValue <= 1000000) {
					gridValue = (gridValue / 1000).toFixed(2) + ' K';
				}

				if (gridValue >= 1000000 && gridValue <= 1000000000) {
					gridValue = (gridValue / 1000000).toFixed(2) + ' M';
				}

				var y = document.createElement('div');

				y.classList.add( yClassName );
				y.style[ yPropertyName ] = top + 'px';
				y.innerHTML = '<div>' + gridValue + '</div>';

				container.appendChild( y );

				value += step;

			}

			el.appendChild( container );

			return this;
		},

		drawX: function(el, options) {

			var columns = options.columns;

			var keys = Object.keys(columns);
			var columnsCount = keys.length;
			var columnSize = Math.round((options.maxWidth - options.barGap * (columnsCount + 1)) / columnsCount);

			if (options.vertical) {
				columnSize = options.defaultWidth;
			}

			var max = this.findMax(columns);
			var total = this.totalSum(columns);

			if (!options.vertical) {
				if (columnSize < options.defaultColumnWidth) { //options.defaultColumnWidth = 65
					$(this).addClass('bar-titles-vertical');
				}
				columnSize = (columnSize / (options.maxWidth / 100));
			}


			keys.sort(function(a,b){ return parseInt(a) - parseInt(b); });


			var bars = document.createDocumentFragment();

			for (var k in keys) {

				if (keys.hasOwnProperty(k)) {

					var key = keys[k];

					var column = columns[key];

					var localMax = 0;
					var localSum = 0;
					var localMaxHeight = 0;

					//sort values desc
					column.sort(function (a, b) { return b.value - a.value; });

					column.forEach(function(bar){
						localMax = bar.value > localMax ? bar.value : localMax;
						localSum += bar.value;
					});

					localMaxHeight = (localMax * options.maxHeight / max);

					var text = key.toString();

					//it's timestamp, so let's format it
					if (text.length === 10 && text == parseInt(text)) {
						text = this.formatDate(new Date(text * 1000));
					}

					var bar = document.createElement('div');
					var barTitle = document.createElement('div');
					var barValue = document.createElement('div');


					barTitle.classList.add('bar-title');
					barTitle.textContent = text;

					barValue.classList.add('bar-value');
					barValue.style[ options.vertical ? 'width' : 'height' ] = localMaxHeight;

					bar.classList.add('bar');

					if (options.vertical) {
						bar.style.height = columnSize;
					} else {
						bar.style.width = columnSize + '%';
						bar.style.marginLeft = options.barGapPercent + '%';
					}

					bar.setAttribute('data-id', key);

					bar.appendChild(barTitle);
					bar.appendChild(barValue);


					var bottom = 0;
					var previousBottom = 0;
					var previousHeight = 0;

					var partial = document.createDocumentFragment();

					column.forEach(function (bar) {

						var height = localMaxHeight / localMax * bar.value;
						var percentage = (bar.value / (total / 100)).toFixed(2);

						bottom = previousHeight + previousBottom;

						var barLine = document.createElement('div');

						barLine.classList.add('bar-line');

						barLine.setAttribute('data-percentage', percentage + '%');
						barLine.setAttribute('data-name', bar.name);
						barLine.setAttribute('data-value', bar.value);

						barLine.style.backgroundColor = bar.color;
						barLine.style[ options.vertical ? 'width' : 'height' ] = height + 'px';
						barLine.style[ options.vertical ? 'left' : 'bottom' ] = bottom + 'px';

						partial.appendChild(barLine);

						previousBottom = bottom;
						previousHeight = height;

					});

					barValue.appendChild( partial );

					var barSum = document.createElement('div');

					barSum.classList.add('bar-value-sum');

					barSum.style[ options.vertical ? 'left' : 'bottom' ] = previousBottom + previousHeight + 'px';

					barSum.textContent = Number( localSum.toFixed(4)).toString(); // trim trailing zeros


					bar.appendChild(barSum);

				}

				bars.appendChild(bar);

			}

			//$(this).append(bars);
			el.appendChild(bars);

			return this;
		},

		drawToolTip : function(el) {

			var tooltipExist = $(el).find('.tooltip').length > 0;

			if (!tooltipExist) {

				var tooltipTitle = document.createElement('div');

				tooltipTitle.classList.add('tooltip-title');


				var tooltipValue = document.createElement('div');

				tooltipValue.classList.add('tooltip-change');


				var tooltip = document.createElement('div');

				tooltip.classList.add('tooltip');
				tooltip.classList.add('tooltip-mobile');
				tooltip.classList.add('hidden');

				tooltip.style.top = 0;
				tooltip.style.left = 0;

				tooltip.appendChild( tooltipTitle );
				tooltip.appendChild( tooltipValue );

				el.appendChild(tooltip);

			}

			return this;
		},

		drawLegend : function(el, options) {

			var bars = options.bars;

			var legend = document.createElement('div');

			legend.classList.add('legend');
			legend.classList.add('bar-legend');


			bars.forEach(function(bar){

				var checkbox = document.createElement('div');

				checkbox.classList.add( 'checkbox' );
				checkbox.classList.add( options.hiddenBars.indexOf(bar.name) === -1 ? 'checked' : '' );
				checkbox.style.backgroundColor = bar.color;


				var legendItem = document.createElement('div');

				legendItem.classList.add( 'legend-item' );
				legendItem.style.color = bar.color;
				legendItem.textContent = bar.name;


				var legendItemWrapper = document.createElement('div');

				legendItemWrapper.classList.add( 'legend-item-wrapper' );
				legendItemWrapper.appendChild( checkbox );
				legendItemWrapper.appendChild( legendItem );


				legend.appendChild( legendItemWrapper );

			});

			el.parentNode.appendChild( legend );

			return this;
		},

		subscribe : function(el, options) {


			var self = this;


			var clicks = 0;

			var timer = null;

			var delay = 200;


			var $el = $(el);

			var $tooltip = $el.find('.tooltip');

			var $barLines = $el.find('.bar-line');

			var $legendItemWrapper = $el.parent().find('.legend-item-wrapper');


			$barLines.on('mousemove', function(e){

				var $currentTarget = $(e.currentTarget);

				$currentTarget.parents('.bar').addClass('bar-active');

				$tooltip.css({
					top: e.clientY - 65,		// + $(this).offset().top
					left: e.clientX - 65		// + $(this).offset().left
				});

				$tooltip
					.find('.tooltip-title')
					.html( $currentTarget.data('name') );

				$tooltip
					.find('.tooltip-change')
					.html( $currentTarget.data('value') + '<small>' + $currentTarget.data('percentage') + '</small>' );

				$tooltip.removeClass('hidden');
			});

			$barLines.on('mouseleave', function(e){

				$tooltip.addClass('hidden');

				$(e.currentTarget).parents('.bar').removeClass('bar-active');
			});


			$legendItemWrapper.on('mouseleave', function(e){

				var barName = $(e.currentTarget).find('.legend-item').html();

				var $bar = $el.find('.bar-line[data-name="' + barName + '"]');

				$bar.removeClass('active');
			});


			$legendItemWrapper.on('mouseenter', function(e){

				var barName = $(e.currentTarget).find('.legend-item').html();

				var $bar = $el.find('.bar-line[data-name="' + barName + '"]');

				$bar.addClass('active');
			});


			$legendItemWrapper.on('click', function(e){

				e.preventDefault();

				var $currentTarget = $(e.currentTarget);

				clicks++;

				if (clicks === 1) {

					timer = setTimeout(function(){

						clearTimeout(timer);

						var name = $currentTarget.find('.legend-item').html();

						var isChecked = $currentTarget.find('.checkbox').hasClass('checked');

						$currentTarget.find('.checkbox').toggleClass('checked');

						if (isChecked) {

							options.hiddenBars.push(name);

						} else {

							var index = options.hiddenBars.indexOf(name);

							if (index >= 0) {

								options.hiddenBars.splice(index, 1);

							}

						}

						self.update(el, options);

						clicks = 0;

					}, delay);

				} else {

					clearTimeout(timer);

					var $checkbox = $currentTarget.find('.checkbox');

					var $checkboxes = $currentTarget.parent().find('.checkbox.checked');

					var checkedCount = $checkboxes.length;

					if (checkedCount === 1 && $checkbox.hasClass('checked')) {

						$currentTarget.parent().find('.checkbox').addClass('checked');

					} else {

						$currentTarget.parent().find('.checkbox').removeClass('checked');

						$checkbox.addClass('checked');

					}

					var checkboxes = [];

					$currentTarget.parent().find('.checkbox:not(.checked)').each(function(){

						checkboxes.push( $(this).next('.legend-item').html() );

					});

					options.hiddenBars = checkboxes;

					self.update(el, options);


					clicks = 0;

				}
			});


			$legendItemWrapper.on('dblclick', function(e){

				e.preventDefault();
			});


			return this;
		}
	};


	$.fn[pluginName] = function ( options ) {

		return this.each(function () {

			if (!$.data(this, "plugin_" + pluginName)) {

				$.data(this, "plugin_" + pluginName, new Plugin(this, options));

			}

		});

	};


})( jQuery, window, document );
