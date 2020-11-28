 var optionsApi =Vue.resource('/')

 Vue.component('option-row',{
 template: '<div {{option.name}} />'
 });
 

Vue.component('options-list', {
props: ['options' ] ,
  template: '<div><div v-for="option in options"/></div>'
})

var app = new Vue({
  el: '#app',
  template: '<options-list :options="options"/>' ,
  data: {
     options: [
     {name: 'point'},
     {name: 'velik'}
    ]
  }
})
;
 
