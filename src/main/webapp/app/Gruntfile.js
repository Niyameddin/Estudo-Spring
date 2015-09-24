// Gruntfile.js

var fs = require('fs');
var xml2js = require('xml2js');
var parseDataFromPomXml = function() {
	var data = [];
	var parser = new xml2js.Parser();
	var pomXml = fs.readFileSync('../../../../pom.xml', "utf8");
	parser.parseString(pomXml, function (err, result){
        data.version = result.project.version[0];
        data.name = result.project.artifactId[0];
        data.group = result.project.groupId[0];        
    });
    return data;
};

module.exports = function(grunt) {

  // ===========================================================================
  // CONFIGURE GRUNT ===========================================================
  // ===========================================================================

  grunt.initConfig({

    pkg: grunt.file.readJSON('package.json'),
    banner: '/*!\n' +
            ' * <%= pkg.name%> v<%= pkg.version %> (<%= pkg.homepage %>)\n' +
            ' * <%= grunt.template.today("dd/mm/yyyy") %> <%= pkg.author %>\n' +
            ' * Licensed under the <%= pkg.license %> license\n' +
            ' */\n',

    	// configure jshint to validate js files -----------------------------------
	    jshint: {
	      options: {
	        reporter: require('jshint-stylish')
	      },
	      
	      build: ['Gruntfile.js', 'assets/src/scripts/**/*.js']
	    },

	    // configure uglify to minify js files -------------------------------------
	    uglify: {
	      options: {
	      	banner: '<%= banner %>',
	        mangle:false
	      },
	      build: {
	        files: {
	          'assets/dist/scripts/scripts.min.js': 'assets/src/scripts/**/*.js'
	        }
	      }
	    },

	    // configure cssmin to minify css files ------------------------------------
	    cssmin: {
	      options: {
	        banner: '<%= banner %>',
	      },
	      build: {
	        files: {
	          'assets/dist/styles/styles.min.css': 'assets/src/styles/**/*.css'
	        }
	      }
	    },

	    // configure to copy files ------------------------------------------
	    copy: {
		  main: {
		    files: [
		      {expand: true, dest: 'assets/dist/images', cwd: 'assets/src/images', src: '**'},
		    ],
		  },
		},

		// configure watch to auto update ------------------------------------------
	    watch: {  
          options: {
              livereload: true
          },

		  stylesheets: { 
			files: ['assets/src/styles/**/*.css'], 
			tasks: ['cssmin'] 
		   },

		  scripts: { 
		    files: 'assets/src/scripts/**/*.js', 
		    tasks: ['jshint', 'uglify'] 
		  },

		  assets: {
		  	files: 'assets/src/images/**',
		  	tasks: ['copy']
		  },

		},

		// configure express server ------------------------------------------
		express: {
          all: {
            options: {
              port: 9000,
              hostname: "localhost",
              bases: [__dirname],
              livereload: true
            }
          }
        },

        // configure to open browser ------------------------------------------
        open: {
          server: {
            path: 'http://localhost:<%= express.all.options.port%>',
          }
        } 
  });

  // ===========================================================================
  // LOAD GRUNT PLUGINS ========================================================
  // ===========================================================================
  
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-express');
  grunt.loadNpmTasks('grunt-open');

  // ============= // CREATE TASKS ========== //
  
  grunt.registerTask('default', ['jshint', 'uglify', 'cssmin', 'copy', 'express', 'open',  'watch']); 

  grunt.registerTask('updateProjectVersion', function (){
  	console.log("--Atualizando versão do projeto, utilizando: pom.xml");
  	var pkg = "package.json";
  	var bower = "bower.json";
  	if (!grunt.file.exists(pkg)) {
        grunt.log.error("Erro: Arquivo " + pkg + " não encontrado!"); 
    }else if(!grunt.file.exists(bower)){
    	grunt.log.error("Erro: Arquivo " + bower + " não encontrado!"); 
    }else{
    	var projectFile = grunt.file.readJSON(pkg);
    	var bowerFile = grunt.file.readJSON(bower);
    	var data = parseDataFromPomXml(); 
    	projectFile.version = data.version;
    	projectFile.name = data.name;
    	projectFile.title = data.name;
    	bowerFile.version = data.version;
    	bowerFile.name = data.name;
    	bowerFile.title = data.name;   	
    	grunt.file.write(pkg, JSON.stringify(projectFile, null, 2));
    	grunt.file.write(bower, JSON.stringify(bowerFile, null, 2));
    	console.log("--Versão atualizada: "+ data.version);
    }
  });
};