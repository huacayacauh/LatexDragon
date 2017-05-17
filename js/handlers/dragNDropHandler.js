/**
 * Module responsible of handling the drag & drop event.
 * Currently not used and not finished. Should be working.
 * @module dragNDropHandler
 */
var self = module.exports = {

	setEvents: (obj) => {
		for (var i in obj.ids)
			$('#' + obj.ids[i]).addClass('dragndrop')

		$('.dragndrop').attr('draggable', 'true')
		$('.dragndrop').on('dragstart', self.dragstartHandler)
		$('.dragndrop').on('dragover', self.dragoverHandler)
		$('.dragndrop').on('drop', self.dropHandler)
	},

	dragstartHandler: function (event) {
		event.originalEvent.dataTransfer.setData('text/plain', $(this).attr('id'))
	},

	dragoverHandler: function (event) {
		event.preventDefault()
	},

	dropHandler: function (event) {
		event.preventDefault()
		console.log(event.originalEvent.dataTransfer.getData('text') + '|' + $(this).attr('id'))
		/*$(this).text($('#'+event.originalEvent.dataTransfer.getData('text')).text())
		$('#'+event.originalEvent.dataTransfer.getData('text')).text(tmp)*/
	}
}
